# Upload Cloudinary Project

## Giới thiệu

Đây là project demo upload và xóa file lên Cloudinary.

## Cấu trúc thư mục

- `Java_SpringBoot` : sử dụng ngôn ngữ java để thao tác với file trong Cloudinary
- `PHP` : sử dụng ngôn ngữ PHP để theo tác với file trong Cloudinary (Chưa phát triển)

## Tính năng

- Upload file đơn lên Cloudinary
- Upload nhiều file lên Cloudinary
- Xóa file trên Cloudinary theo URL (có thể sửa để truyền url thông qua JSON)

# Hướng dẫn

## 1. **Lấy API KEY**

[`API KEY Cloudinary`](https://console.cloudinary.com/app/.../settings/api-keys) (yêu cầu đăng nhập)

## 2. **Java**

- Thay đổi application.properties

```sh
cloudinary.cloud_name=
cloudinary.api_key=
cloudinary.api_secret=
```

- Build và chạy ứng dụng

```sh
cd Java_SpringBoot/upLoad-Cloudinary-Java
./mvnw spring-boot:run
```

- API endpoints:
  - `POST /cloud/upload` : Upload 1 file (form-data, key: `file`)
  - `POST /cloud/upload-multiple` : Upload nhiều file (form-data, key: `files`)
  - `DELETE /cloud/delete` : Xóa file theo URL (param: `fileUrl`)

## 3. PHP

- Chưa phát triển

# Tham khảo

- [`Cloudinary Java SDK`](https://cloudinary.com/documentation/java_integration)
- [`Cloudinary PHP SDK`](https://cloudinary.com/documentation/php_integration)

### Tác giả: DELHT
