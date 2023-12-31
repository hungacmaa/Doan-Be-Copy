package com.be_project.service.impl;

import com.be_project.entity.Censor;
import com.be_project.entity.Post;
import com.be_project.entity.dto.CensorDto;
import com.be_project.entity.dto.ListURLDto;
import com.be_project.entity.model.Detection;
import com.be_project.entity.model.Predict;
import com.be_project.entity.model.PredictResponse;
import com.be_project.repository.IAccountRepo;
import com.be_project.repository.ICensorRepo;
import com.be_project.repository.IPostRepo;
import com.be_project.service.ICensorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CensorService implements ICensorService {

    @Autowired
    private ICensorRepo censorRepo;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private IAccountRepo accountRepo;
    @Autowired
    private IPostRepo postRepo;

    @Override
    public List<Censor> getAll() {
        return censorRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    @Async
    public void createCensor(Post post, ListURLDto listURLDto) {
        Censor censor = Censor.builder()
                .post(post)
                .createdAt(post.getCreatedAt())
                .status("Chờ kiểm duyệt")
                .build();
        censor = censorRepo.save(censor);
        post = postRepo.findById(post.getId()).get();
        String categoryProduct = post.getCategoryProduct().getName();
        try{
            if (categoryProduct.contains("Quần") || categoryProduct.contains("Áo")) {
                // lấy list images
                try {
                    // call api
                    PredictResponse predictResponse = webClientBuilder.build()
                            .post()
                            .uri("http://localhost:5000/predict")
                            .body(Mono.just(listURLDto), ListURLDto.class)
                            .retrieve()
                            .bodyToMono(PredictResponse.class)
                            .block();

                    // Xử lý
                    int numValidImage = 0;
                    for (Predict predict : predictResponse.getData()) {
                        for (Detection detection : predict.getBoxes()) {
                            String objectName = detection.getName();
                            float conf = detection.getConfidence();
                            // Nếu tồn tại một đối tượng thỏa mãn thì dừng xét các đối tượng khác
                            if ((objectName.equals("Tshirt")
                                    || objectName.equals("dress")
                                    || objectName.equals("jacket")
                                    || objectName.equals("pants")
                                    || objectName.equals("shirt")
                                    || objectName.equals("short")
                                    || objectName.equals("skirt")
                                    || objectName.equals("sweater"))
                                    && conf >= 0.25) {
                                // tăng số lượng ảnh thỏa mãn
                                numValidImage++;
                                break;
                            }
                        }
                    }
                    // kết luận
                    int numImage = listURLDto.getImg_urls().size();
                    if (numValidImage >= 0.65 * numImage) {
                        censor.setStatus("Đã kiểm duyệt");
                        censor.setResult("Duyệt");
                        censor.setReason("Thỏa mãn");
                        censor.setReviewer(accountRepo.findByUsername("predictsystem"));
                        censor.setModifiedAt(LocalDate.now());

                        post.setStatus("Chưa trao đổi");
                        censorRepo.save(censor);
                        postRepo.save(post);

                    } else if (numValidImage <= 0.2 * numImage) {
                        censor.setStatus("Đã kiểm duyệt");
                        censor.setResult("Khóa");
                        censor.setReason("Quá nhiều ảnh không hợp lệ");
                        censor.setReviewer(accountRepo.findByUsername("predictsystem"));
                        censor.setModifiedAt(LocalDate.now());

                        post.setStatus("Khóa");
                        censorRepo.save(censor);
                        postRepo.save(post);

                    } else {
                        censorRepo.save(censor);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public Censor editCensor(CensorDto censorDto, long censorId) {
        // lấy censor theo id truyền vào từ db
        Optional<Censor> optionalCensor = censorRepo.findById(censorId);
        if (optionalCensor.isPresent()) {
            Censor censor = optionalCensor.get();
            // sửa thông tin censor đó theo censorDto

            // xử lý lần censor đó và sửa trạng thái bài đăng tương ứng với censor đó

            // lưu lần censor đó và lưu bài đăng đó với trạng thái mới

            return null;
        } else return null;
    }

    @Override
    public Boolean deleteCensor(Censor censor) {
        return null;
    }

    @Override
    public Censor acceptCensor(long censorId, CensorDto censorDto) {
        Optional<Censor> data = censorRepo.findById(censorId);
        if (data.isPresent()) {
            Censor censor = data.get();
            if (censor.getStatus().equals("Chờ kiểm duyệt")) {
                censor.setReason("Thỏa mãn");
                censor.setResult("Duyệt");
                censor.setStatus("Đã kiểm duyệt");
                ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
                censor.setModifiedAt(LocalDate.now(vietnamZone));
                censor.setReviewer(censorDto.getAccount());

                Post post = censor.getPost();
                post.setStatus("Chưa trao đổi");
                postRepo.save(post);

                censor = censorRepo.save(censor);

                return censor;
            } else {
                return censor;
            }
        }
        return null;
    }

    @Override
    public Censor rejectCensor(long censorId, CensorDto censorDto) {
        Optional<Censor> data = censorRepo.findById(censorId);
        if (data.isPresent()) {
            Censor censor = data.get();
            if (censor.getStatus().equals("Chờ kiểm duyệt")) {
                censor.setReason(censorDto.getReason());
                censor.setResult("Khóa");
                censor.setStatus("Đã kiểm duyệt");
                ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
                censor.setModifiedAt(LocalDate.now(vietnamZone));
                censor.setReviewer(censorDto.getAccount());

                Post post = censor.getPost();
                post.setStatus("Khóa");
                postRepo.save(post);

                censor = censorRepo.save(censor);

                return censor;
            } else {
                return censor;
            }
        }
        return null;
    }

    @Override
    public Censor getCensorById(long censorId) {
        return censorRepo.findById(censorId).get();
    }
}
