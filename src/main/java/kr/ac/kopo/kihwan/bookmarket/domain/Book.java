package kr.ac.kopo.kihwan.bookmarket.domain;

import jakarta.validation.constraints.*;
import kr.ac.kopo.kihwan.bookmarket.validator.BookId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


//@Setter
//@Getter
//@NoArgsConstructor
@Data
public class Book {
//    유효성 검사 추가 2025-05-14
    @BookId //20250521 추가
    @Pattern(regexp = "isbn[0-9]+")
    private String bookID;  //도서 ID

    @Size(min = 4, max = 50)
    private String name;    //도서 제목

    @Min(value = 0)
    @Digits(integer = 8, fraction = 2)
    @NotNull
    private BigDecimal unitPrice;   //가격

    private String author;  //저자
    private String publisher;   //출판사
    private String description; //설명
    private String category;    //분류
    private long unitInStock;   //재고수
    private String releaseDate; //출판일(월/년)
    private String condition;   //신규,중고,전자책
    private String fileName; //도서 이미지 파일
    private MultipartFile bookImage; //업로드된 도서 이미지 파일
}
