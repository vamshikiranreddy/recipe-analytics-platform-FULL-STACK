# 🍲 Intelligent Recipe Data Platform  

A full-stack application for managing, exploring, and analyzing large-scale recipe datasets. Built with **Spring Boot**, **PostgreSQL**, **Node.js**, and **HTML** — it powers clean data pipelines, REST APIs, and an intuitive web UI for recipe browsing.  

---

## 📖 Description  
The Intelligent Recipe Data Platform automates the ingestion and validation of JSON recipe datasets. It provides advanced APIs to search, filter, and retrieve recipes based on cuisine, cooking time, and nutrition. A **Node.js + HTML UI** lets users explore data visually, while APIs are available for integration with other systems.  

---

## 🛠 Tech Stack  

- **Backend:** Spring Boot (REST APIs), Hibernate JPA  
- **Database:** PostgreSQL with JSONB for flexible nested data  
- **Frontend:** Node.js + HTML (with Bootstrap)  
- **Build Tool:** Maven  
- **Libraries:** Jackson (JSON parsing), HikariCP  

---

## ✨ Features  

- 📥 Automated JSON data cleaning and import  
- 🔍 Search recipes by **title, cuisine, rating, cooking time**  
- 🧮 Filter recipes by **nutritional values** (calories, protein, fat, etc.)  
- 🌐 REST APIs for integrations and testing with Postman  
- 🖥 Lightweight **UI for browsing recipes**  

---

## 🚀 Getting Started  

### Prerequisites  
- Java 22+  
- Maven  
- PostgreSQL  
- Node.js  

### Backend Setup (Spring Boot)  
🔹 1. Get All Recipes (paginated)
👉 URL: http://localhost:8080/api/recipes?page=1&size=5
✅ This will return the first 5 recipes sorted by rating (descending).

🔹2. Search by Cuisine
👉 URL: http://localhost:8080/api/recipes/search?cuisine=Red%20Velvet%20Cake
✅ Returns only recipes under the "Red Velvet Cake" cuisine.

🔹3. Search with Calories Filter
👉 URL: http://localhost:8080/api/recipes/search?maxCalories=400
✅ Returns only recipes with nutrients.calories <= 400.

🔹 4. Combine Filters
👉 URL: http://localhost:8080/api/recipes/search?cuisine=Red%20Velvet%20Cake&minRating=4.0&maxCalories=400
✅ Returns recipes that are:
From Red Velvet Cake cuisine,
Have rating >= 4.0,
And calories <= 400.

🔹 5. Get Single Recipe by ID
👉 URL: http://localhost:8080/api/recipes/135
✅ Returns recipe with id=135 if it exists.

📸 Screenshots & Demo
🔹 Home Page (UI)
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/9726562b-810f-4837-a985-ab81eae15263" />


🔹 Recipe Search (UI)
<img width="1275" height="442" alt="image" src="https://github.com/user-attachments/assets/866c3862-769c-4585-9627-745bfa05c78d" />

🔹 Recipe Details (UI)
<img width="1127" height="455" alt="image" src="https://github.com/user-attachments/assets/7377b454-d99f-43d1-9533-2eed77ee847b" />


🔹 API Demo – Get All Recipes (Postman)
<img width="1897" height="935" alt="image" src="https://github.com/user-attachments/assets/714266c9-a1bb-4cbe-b4fc-a7f6cfb1b533" />

📂 Project Structure
css
Copy code
recipe-management-system/
├── backend/ (Spring Boot APIs)
│   ├── src/main/java/com/securin/recipes
│   └── src/main/resources/data/US_recipes_clean.json
├── frontend/ (Node.js + HTML UI)
│   ├── public/
│   └── server.js
├── screenshots/ (UI & API images)
├── pom.xml
└── README.md

## 🚀 Quickstart

Follow these steps to run the project locally:

### 1. Clone the Repository

git clone https://github.com/vamshikiranreddy/recipe-analytics-platform-FULL-STACK.git
cd recipe-analytics-platform-FULL-STACK
### 2. Setup Backend (Spring Boot + PostgreSQL)
Make sure PostgreSQL is installed and running.
Create a database named recipes.
Update application.properties with your DB username & password.
Run the backend:
mvn clean spring-boot:run
➡ The API will start at: http://localhost:8080
### 3. Setup Frontend (Node.js + HTML)
cd frontend
npm install
npm start
➡ The frontend will start at: http://localhost:3000
### 4. Test API
You can test endpoints like:
GET http://localhost:8080/api/recipes

🎯 Resume Highlights
Designed and implemented a full-stack recipe management system.

Built REST APIs for advanced querying using Spring Boot + PostgreSQL JSONB.

Developed a lightweight UI with Node.js + HTML for recipe browsing.

Automated JSON data validation and cleaning for production readiness.

📜 License
MIT License. Free to use and adapt.

🤝 Contributing
Contributions are welcome! Please fork the repo and open a PR.

