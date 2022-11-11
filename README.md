# Introduction

This is a demo project that demonstrate the combination of **Vue.js 3** as UI framework and **Spring Boot 2.7** as backend services providing REST APIs.

Customer can visit the home page and type in any product name they want. The website will provided up to 10 most related items. Customer can click on the image, name, description of the item and will be redirected to a product detail page, which display all details regarding the selected item. The detail page will also display additional recommendation of items that is related.

# How to run this project

## Source Code

The project is hosted on GitHub.

Please use `git clone https://github.com/QiyuLi/demo-product.git` to check out this repository.

After checking out, use `cd demo-product` to go to project base folder, where this *README.md* file is located.

## Backend - Spring Boot

>The Spring Boot API endpoint is based on Spring Boot 2.7.

### Requirements

Please make sure JDK 11+ is installed. If you are on Debian/Ubuntu, please use `sudo apt install -y openjdk-11-jdk` to install the necessary JDK runtime.

### Start backend

Please make sure you entered the backend folder by `cd backend`.

1. The backed API endpoint can now be started by the running `./gradlew bootRun`

## Frontend - Vue.js 3

>The frontend is based on Vue.js 3 + Vite.

### Requirements

Node.js v16+ is required.

### Start backend

Please open a new terminal and make sure you entered the frontend folder by `cd frontend`.

1. First run `npm install` to make sure all packages are installed.
2. Then run `npm run dev` to start dev server.

3. Unit tests can be executed via `npm run test:unit` 
4. e2e tests can be executed via `npm run test:e2e`.

## How to browse the project

If both back end and front end are started. e.g.

```shell
./gradlew bootRun # port 8080
npm run dev # port 5173
```

Then you can open browser and visit `http://localhost:5173`
