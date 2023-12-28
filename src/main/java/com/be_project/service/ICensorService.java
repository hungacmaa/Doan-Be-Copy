package com.be_project.service;

import com.be_project.entity.Censor;
import com.be_project.entity.Post;
import com.be_project.entity.dto.CensorDto;
import com.be_project.entity.dto.ListURLDto;

import java.util.List;

public interface ICensorService {
    List<Censor> getAll();
    void createCensor(Post post, ListURLDto listURLDto);
    Censor editCensor(CensorDto censorDto, long censorId);
    Boolean deleteCensor(Censor censor);

    Censor acceptCensor(long censorId, CensorDto censorDto);
}
