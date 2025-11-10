# BookMarket - 온라인 서점 프로젝트

이 프로젝트는 Spring Boot를 기반으로 한 온라인 서점 웹 애플리케이션입니다. 도서 조회, 장바구니, 주문 처리, 사용자 인증 및 관리자 기능을 포함하고 있습니다.

## 주요 기술 스택

* **언어**: Java
* **프레임워크**: Spring Boot
* **뷰 템플릿**: Thymeleaf
* **보안**: Spring Security
* **빌드 도구**: Gradle

## 주요 기능

프로젝트는 도서 관리, 장바구니, 주문, 인증 등 여러 모듈로 구성되어 있습니다.

### 1. 도서 기능 (`BookController`)

* **도서 목록 조회 (`/books`)**: 등록된 모든 도서의 목록을 보여줍니다.
* **도서 상세 조회 (`/book`)**: 특정 도서의 상세 정보를 ISBN(ID)을 기준으로 조회합니다.
* **도서 추가 (`/books/add`)**: (관리자 전용) 새로운 도서를 시스템에 등록합니다.
    * 도서 정보 입력 폼을 제공하고, POST 요청으로 도서 정보(이미지 파일 포함)를 받아 저장합니다.
    * Spring Validation 및 사용자 정의 Validator(`BookValidator`, `BookIdValidator`)를 사용하여 입력 데이터를 검증합니다.

### 2. 장바구니 기능 (`CartController`)

* **세션 기반 장바구니**: 사용자의 장바구니 정보가 HTTP 세션에 저장됩니다.
* **장바구니 추가 (`/cart/add/{bookId}`)**: 도서 상세 페이지 등에서 특정 도서를 장바구니에 추가합니다.
* **장바구니 조회 (`/cart`)**: 현재 세션에 담긴 장바구니 내역을 보여줍니다.
* **장바구니 항목 삭제 (`/cart/remove/{bookId}`)**: 장바구니에서 특정 도서를 제거합니다.

### 3. 주문 처리 기능 (`OrderController`)

* **다단계 주문 프로세스**: 장바구니에 담긴 상품들을 기반으로 주문을 생성하는 다단계 프로세스를 제공합니다.
    1.  **주문 시작 (`/order/add`)**: 장바구니 ID(세션 ID)를 기반으로 주문을 시작합니다.
    2.  **고객 정보 입력 (`/order/customer`)**: 주문자 정보를 입력받습니다.
    3.  **배송지 정보 입력 (`/order/shipping`)**: 배송지 정보를 입력받습니다.
    4.  **주문 확인 (`/order/confirmation`)**: 입력된 모든 정보(주문 상품, 고객, 배송지)를 확인합니다.
    5.  **주문 제출 (`/order/submit`)**: 주문을 최종 제출하고, 장바구니를 비웁니다.
    6.  **주문 완료 (`/order/finished`)**: 주문 완료 페이지(감사 페이지)로 이동합니다.
* **주문 관리 (관리자)**:
    * **주문 목록 (`/order/list`)**: 전체 주문 목록을 확인합니다.
    * **주문 수정 (`/order/edit/{orderId}`)**: 주문 상태 등을 수정합니다.
    * **주문 취소 (`/order/cancel/{orderId}`)**: 특정 주문을 취소합니다.

### 4. 인증 및 보안 (`SecurityConfig`, `LoginController`)

* **Spring Security 적용**: `SecurityConfig`를 통해 웹 보안을 설정합니다.
* **로그인/로그아웃**:
    * 사용자 정의 로그인 페이지 (`/login`)를 제공합니다.
    * 로그아웃 처리를 지원합니다 (`/logout`).
* **In-Memory 인증**: 임시로 In-Memory 방식의 사용자 인증(user, admin)을 사용합니다.
* **권한 관리**:
    * `/books/add`, `/order/list`, `/order/edit/*` 등 관리자 기능은 'ADMIN' 역할을 가진 사용자만 접근할 수 있도록 제한됩니다.
    * 정적 리소스(`/images/**`, `/js/**`) 및 특정 페이지(`/`, `/books`, `/book`, `/login`)는 모든 사용자가 접근 가능합니다.

### 5. 데이터 관리 (Repository)

* **In-Memory Repository**: 모든 데이터(도서, 장바구니, 주문)는 데이터베이스 대신 In-Memory 구현체(`BookRepositoryImpl`, `CartRepositoryImpl`, `OrderRepositoryImpl`)를 통해 관리됩니다.
* **참고**: 애플리케이션 재시작 시 모든 데이터는 초기화됩니다.

### 6. 예외 처리

* `@ControllerAdvice`를 사용한 전역 예외 처리를 구현합니다 (`CommonException`).
* 도서 ID 조회 실패 등 특정 예외(`BookIdException`, `CategoryException`)에 대해 별도의 에러 페이지(`errorBook.html`)로 안내합니다.

## 프로젝트 구조 (주요 파일)

```text
.
├── build.gradle                # Gradle 빌드 설정
├── settings.gradle
├── gradlew
├── gradlew.bat
├── src/main/
│   ├── java/kr/ac/kopo/kihwan/bookmarket/
│   │   ├── BookMarketApplication.java  # Spring Boot 시작점
│   │   ├── config/
│   │   │   ├── ResourceConfig.java     # 정적 리소스 설정
│   │   │   ├── SecurityConfig.java     # Spring Security 설정
│   │   │   └── ValidationConfig.java   # 유효성 검사 메시지 설정
│   │   ├── controller/
│   │   │   ├── BookController.java     # 도서 관련 컨트롤러
│   │   │   ├── CartController.java     # 장바구니 관련 컨트롤러
│   │   │   ├── LoginController.java    # 로그인/로그아웃 컨트롤러
│   │   │   ├── OrderController.java    # 주문 관련 컨트롤러
│   │   │   └── WelcomeController.java  # 메인 페이지 컨트롤러
│   │   ├── domain/
│   │   │   ├── Address.java
│   │   │   ├── Book.java
│   │   │   ├── Cart.java
│   │   │   ├── CartItem.java
│   │   │   ├── Customer.java
│   │   │   ├── Order.java
│   │   │   ├── OrderItem.java
│   │   │   └── Shipping.java
│   │   ├── exception/
│   │   │   ├── BookIdException.java
│   │   │   ├── CartException.java
│   │   │   ├── CategoryException.java
│   │   │   └── CommonException.java
│   │   ├── repository/
│   │   │   ├── BookRepository.java (Interface)
│   │   │   ├── BookRepositoryImpl.java
│   │   │   ├── CartRepository.java (Interface)
│   │   │   ├── CartRepositoryImpl.java
│   │   │   ├── OrderProRepository.java (Interface)
│   │   │   ├── OrderRepository.java (Interface)
│   │   │   └── OrderRepositoryImpl.java
│   │   ├── service/
│   │   │   ├── BookService.java (Interface)
│   │   │   ├── BookServiceImpl.java
│   │   │   ├── CartService.java (Interface)
│   │   │   ├── CartServiceImpl.java
│   │   │   ├── OrderProService.java (Interface)
│   │   │   ├── OrderService.java (Interface)
│   │   │   └── OrderServiceImpl.java
│   │   └── validator/
│   │       ├── BookId.java (Annotation)
│   │       ├── BookIdValidator.java
│   │       ├── BookValidator.java
│   │       └── UnitsInStockValidator.java
│   │
│   └── resources/
│       ├── application.properties    # 애플리케이션 설정
│       ├── messages/
│       │   └── messages.properties   # 유효성 검사 메시지
│       ├── static/
│       │   ├── images/
│       │   │   ├── isbn0001.jpg
│       │   │   ├── isbn0002.jpg
│       │   │   └── (etc...)
│       │   ├── js/
│       │   │   └── controllers.js
│       │   ├── welcome.html
│       │   └── welcome2.html
│       └── templates/                # Thymeleaf 템플릿 (HTML)
│           ├── addBook.html
│           ├── book.html
│           ├── books.html
│           ├── cart.html
│           ├── errorBook.html
│           ├── errorCommon.html
│           ├── login.html
│           ├── orderCancelled.html
│           ├── orderConfirmation.html
│           ├── orderCustomerInfo.html
│           ├── orderEdit.html
│           ├── orderFinished.html
│           ├── orderList.html
│           ├── orderShippingInfo.html
│           └── orderView.html
│
└── src/test/java/ (...) # 테스트 코드



## 실행 방법

1.  **Gradle 빌드**:
    ```bash
    ./gradlew build
    ```
2.  **애플리케이션 실행**:
    ```bash
    ./gradlew bootRun
    ```
    또는
    ```bash
    java -jar build/libs/bookmarket-0.0.1-SNAPSHOT.jar
    ```
3.  **접속**:
    웹 브라우저에서 `http://localhost:8080` (또는 `application.properties`에 설정된 포트)로 접속합니다.

    * **관리자**: ID `admin` / PW `admin`
    * **사용자**: ID `user` / PW `user`
