USE trading_zone;
SET NAMES 'utf8';

INSERT
IGNORE INTO role (id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT
IGNORE INTO account (id, name, username, password, phone, avatar, role_id, status, latitude, longitude)
VALUES (1, 'Nguyen Van A', 'admin', '123456', '0999888777', 'https://genzrelax.com/wp-content/uploads/2022/03/anh-dai-dien-dep-4.jpg', 2, 'Đang hoạt động', '0', '0'),
 (2, 'Jack 5tr', 'user1', '123456', '0999888777', 'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2Favatar-nhom-4-nguoi-26.jpg153f3a27-9d95-4194-9416-7642e6797aa0?alt=media&token=b46492af-4fda-4ac5-a08a-1935a45116cf', 1, 'Đang hoạt động', '0', '0');

INSERT
IGNORE INTO category_product (id, name)
VALUES (1, 'Quần - Áo'), (2, 'Giày - Dép'), (3, 'Điện thoại'), (4, 'Máy tính'), (5, 'Đồng hồ'), (6, 'Sách'), (7, 'Trang sức'), (8, 'Khác');

INSERT IGNORE INTO post (id, title, category_post, category_product_id, description, requirement, address, avatar, created_at, status, account_id,
                   count_view)
VALUES (1, 'Post 1', 'Sản phẩm muốn trao đổi',1, 'Description 1', 'Requirement 1', 'Hà Nội',
        'https://firebasestorage.googleapis.com/v0/b/fir-15eec.appspot.com/o/images%2F7a13fc80826419da94a42bb1b8e5f23f.jpg658c8001-544d-44a1-9d73-93f0b5690e38?alt=media&token=4f428b68-a296-40da-8c3d-41dab48cae64',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (2, 'Post 2', 'Sản phẩm muốn trao đổi', 2, 'Description 2', 'Requirement 2', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1671853906962-RV08WWNIS1LTNE453MOX/Artboard%2B2.jpg?format=750w',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (3, 'Post 3', 'Sản phẩm cần tìm trao đổi', 3, 'Description 3', 'Requirement 3', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (4, 'Post 4', 'Sản phẩm cần tìm trao đổi', 4, 'Description 4', 'Requirement 4', 'Hà Nội',
        'https://shop28decor.com/ct/uploads/2023/07/cac-kieu-chup-anh-san-pham-04-1024x1024-1.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       (5, 'Post 5', 'Sản phẩm muốn trao đổi', 5, 'Description 5', 'Requirement 5', 'Hà Nội',
        'https://mabustudio.com/wp-content/uploads/2020/02/chup-hinh-san-pham-tai-nha-7-scaled.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       (6, 'Post 6', 'Sản phẩm cần tìm trao đổi', 6, 'Description 6', 'Requirement 6', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (7, 'Post 7', 'Sản phẩm cần tìm trao đổi', 1, 'Description 7', 'Requirement 7', 'Hà Nội',
        'https://shop28decor.com/ct/uploads/2023/07/cac-kieu-chup-anh-san-pham-04-1024x1024-1.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       (8, 'Post 8', 'Sản phẩm muốn trao đổi', 2, 'Description 8', 'Requirement 8', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1671853906962-RV08WWNIS1LTNE453MOX/Artboard%2B2.jpg?format=750w',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (9, 'Post 9', 'Sản phẩm muốn trao đổi', 3, 'Description 9', 'Requirement 9', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (10, 'Post 10', 'Sản phẩm muốn trao đổi', 4, 'Description 10', 'Requirement 10', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1671853906962-RV08WWNIS1LTNE453MOX/Artboard%2B2.jpg?format=750w',
        '2023-11-09', 'Chưa trao đổi', 2, 0),
       (11, 'Post 11', 'Sản phẩm muốn trao đổi', 5, 'Description 11', 'Requirement 11', 'Hà Nội',
        'https://shop28decor.com/ct/uploads/2023/07/cac-kieu-chup-anh-san-pham-04-1024x1024-1.jpg', '2023-11-09',
        'Chưa trao đổi', 2, 0),
       (12, 'Post 12', 'Sản phẩm muốn trao đổi', 6, 'Description 12', 'Requirement 12', 'Hà Nội',
        'https://images.squarespace-cdn.com/content/v1/53883795e4b016c956b8d243/1568455300105-1X8EOW3FLT27EN19OO30/chup-anh-san-pham-shynh2019-4.jpg',
        '2023-11-09', 'Chưa trao đổi', 2, 0);


