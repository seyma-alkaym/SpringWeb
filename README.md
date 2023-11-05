# Spring Boot MVC Uygulaması

Bu döküman, Spring Boot tabanlı bir MVC (Model-View-Controller) web uygulaması geliştirmesini anlatır. Uygulama, yazarlar ve kitaplar hakkında bilgi tutmak için kullanılır.

## Adımlar

### Adım 1: Projenin Oluşturulması

1. Yeni bir Spring Boot projesi oluşturdum. İlgili bağımlılıkları ekledim: `Spring Web`, `Spring Data JPA`, `PostgreSQL Database`, `Validation` ve `Lombok`.

2. Proje yapısına uygun olarak, `com.example.demo` paketi altında aşağıdaki paketleri oluşturdum:
    - `service` (Servis sınıfları için)
    - `controller` (Kontrolör sınıfları için)
    - `model` (Veri modellemesi için)
    - `repository` (JPA Repositories için)
    - `dto` (Data Transfer Objects için)

3. `Author` ve `Book` sınıflarını ve veritabanı tablolarını temsil etmek için ilgili model sınıflarını oluşturdum.

```java
// Author.java
@Entity
public class Author {
    // Alanlar ve getter/setter metodları
}

// Book.java
@Entity
public class Book {
    // Alanlar ve getter/setter metodları
}
```

4. `AuthorRepo` ve `BookRepo` adlı JPA repository interfacelerini oluşturdum ve veritabanı işlemleri için kullandım.

### Adım 2: Servis Sınıflarının Oluşturulması

1. `AuthorService` ve `BookService` sınıflarını oluşturdum. Bu sınıflar, iş mantığını içerir ve ilgili veritabanı işlemlerini yapar.

2. Servis sınıfları, ilgili repository sınıflarını kullanarak veritabanı işlemlerini gerçekleştirir. Örneğin:

```java

// AuthorService.java
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepo authorRepo;

    // Metodlar: getAuthors, getAuthorById, addAuthor, updateAuthor, deleteAuthor
}

// BookService.java
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    private final AuthorRepo authorRepo;

    // Metodlar: getBooks, getBookById, addBook, updateBook, deleteBook
}
```

### Adım 3: Kontrolör Sınıflarının Oluşturulması

1. `AuthorController` ve `BookController` sınıflarını oluşturdum. Bu sınıflar, gelen istekleri karşılar ve servis sınıflarını kullanarak istenen işlemleri gerçekleştirir.

2. Kontrolör sınıfları, HTTP isteklerini dinler ve ilgili servis metodlarını çağırır. Örneğin:

```java

// AuthorController.java
@Controller
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    // Metodlar: getAuthors, getAuthorById, addAuthor, updateAuthor, deleteAuthor
}

// BookController.java
@Controller
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // Metodlar: getBooks, getBook, addBook, updateBook, deleteBook
}
```

### Adım 4: Uygulamanın Çalıştırılması

1. Uygulamayı çalıştırmak için Spring Boot uygulamasını başlattım. `DemoApplication.java` adlı ana sınıfı içeren bir sınıfı çalıştırdım.

2. Uygulamayı başladığında, ilgili URL'ler aracılığıyla API metodlarına erişilebilir. Örneğin:

    - `GET /api/v1/author/getAuthors`: Tüm yazarları alır.
    - `GET /api/v1/author/getAuthor/{authorId}`: Belirli bir yazarı alır.
    - `POST /api/v1/author/addAuthor`: Yeni bir yazar ekler.
    - `PUT /api/v1/author/updateAuthor/{authorId}`: Bir yazarı günceller.
    - `DELETE /api/v1/author/deleteAuthor/{authorId}`: Bir yazarı siler.

    - `GET /api/v1/book/getBooks`: Tüm kitapları alır.
    - `GET /api/v1/book/getBook/{bookId}`: Belirli bir kitabı alır.
    - `POST /api/v1/book/addBook`: Yeni bir kitap ekler.
    - `PUT /api/v1/book/updateBook/{bookId}`: Bir kitabı günceller.
    - `DELETE /api/v1/book/deleteBook/{bookId}`: Bir kitabı siler.

### Adım 5: Testler

API'leri test etmek için Postman kullandım.

#### Yazar ile ilgili tesler:

![Tüm yazarları al](https://www6.0zz0.com/2023/11/05/09/423801853.png)

![Belirli bir yazarı al](https://www9.0zz0.com/2023/11/05/09/771728548.png)

![Yeni bir yazar ekle](https://www4.0zz0.com/2023/11/05/09/726794417.png)

![Bir yazarı güncelle](https://www3.0zz0.com/2023/11/05/09/710491846.png)

![Bir yazarı sil](https://www7.0zz0.com/2023/11/05/09/153466229.png)


#### Kitap ile ilgili tesler:

![Tüm kitapları al](https://www7.0zz0.com/2023/11/05/09/918788351.png)

![Belirli bir kitabı al](https://www3.0zz0.com/2023/11/05/09/563404592.png)

![Yeni bir kitap ekle](https://www4.0zz0.com/2023/11/05/09/917108997.png)

![Bir kitabı güncelle](https://www8.0zz0.com/2023/11/05/09/555169621.png)

![Bir kitapı sil](https://www5.0zz0.com/2023/11/05/09/557452853.png)





