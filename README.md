# Online Shop
This project is an e-commerce platform that seamlessly connects sellers and buyers, enabling online transactions much like Shopee or Lazada.
> **Capstone Project** | Team Size: 1  
> **Timeline**: 5/2025 - Now

---

## 📌 Description
This project implements a e-commerce website supporting online buying and selling on the website:
 - Edit user profile with many field and picture.
 - User Login, Find product by serching, add to cart and pay.
 - Seller Login, Add product to him shop.
 - online payment and delivery(is coming)

---

## 🚀 Technologies Used
Backend

- ava, Spring Boot, Restful API, Spring Data JPA, Spring Security, JWT, Spring Profiles,MySQL, Spring testing

- RESTful API design

- UUID for peer identification

Frontend

-  TypeScript, ReactJs.

- Axios for HTTP communication
  
---

## 🧹 Core Functionalities

- Searching for products by name delivers the most relevant results.

- User profile management: view, edit (with role-dependent fields), delete, change password, view notifications.

- User authentication with login, registration for applicant and company roles, and role-based access control.

- Enable sellers to add new items to their catalog and allow users to add products to their shopping cart.

---

## 🦴 Prerequisites
- Java 17+
- Node.js 21+
- Maven
- MySQL Database


## 🛠️ Setup Instructions
1. Clone repository
  - `git clone https://github.com/doandinhhao/online-shop`
2. Run the Spring server application:
  - Execute the mvn clean install command in the online-shop\online-shop-server directory.
  - Modify the online-shop-server\src\main\resources\application.properties file.
      "server.port=8081

      # Database properties
      spring.jpa.hibernate.ddl-auto=create
      spring.datasource.url=jdbc:mysql://localhost:3306/online_shop
      spring.datasource.username=root
      spring.datasource.password=1234
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
      
      # Swagger properties
      springdoc.swagger-ui.tagsSorter=alpha
      
      # JWT
      jwt.secret=YOUR_JWT_SECRET
      
      # Algolia
      # Change it to "true" if Algolia search is enabled.
      algolia.usage=false
      algolia.app.id=YOUR_APP_ID
      algolia.api.key=YOUR_API_KEY
      algolia.index.name=YOUR_INDEX_NAME"
  - Execute the mvn spring-boot:run command in the online-shop\online-shop-server directory.

3. Run the React client application:
   - Execute the npm install command in the online-shop\online-shop-ui directory.
   - Modify the online-shop-ui\src\env-config.ts file.
     
       "export const API_BASE_URL = process.env.API_BASE_URL || 'http://localhost:8080';
        export const JWT_SECRET = process.env.JWT_SECRET || 'YOUR_JWT_SECRET';
        export const ALGOLIA_APP_ID = process.env.ALGOLIA_APP_ID || 'YOUR_APP_ID';
        export const ALGOLIA_API_KEY = process.env.ALGOLIA_API_KEY || 'YOUR_API_KEY';
        export const ALGOLIA_INDEX_NAME = process.env.ALGOLIA_INDEX_NAME || 'YOUR_INDEX_NAME';
        // Change it to "true" if Algolia search is enabled.
        export const ALGOLIA_USAGE = "false";"
  -  Execute the npm start command in the online-shop\online-shop-ui directory.
NOTE: If you don't want to use the Algolia search and therefore to not display the search page, you don't have to define any Algolia properties.
     





