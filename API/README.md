### `Re7let El-Kalemat APIs V1.0 Last Updated 9/11/2024`

**Endpoints:**

- **Signup**

    - **Method:** `POST`
    - **URL:** `/api/signup`
    - **Request:** `{ email: string , password: string , name: string , picture: string , token: string }`
    - **Response:** `{ message: User Added ! , code: 200 , data: null }`

- **Login**

    - **Method:** `POST`
    - **URL:** `/api/login`
    - **Request:** `{ email: string , password: string , token: string }`
    - **Response:** `{ message: User Found , code: 200 , data: null }`

- **Delete Account**

    - **Method:** `DELETE`
    - **URL:** `/api/delete`
    - **Request:** `{ email: string , token: string }`
    - **Response:** `{ message: User Deleted , code: 200 , data: null }`

- **Update Account**

    - **Method:** `PUT`
    - **URL:** `/api/update`
    - **Request:** `{ email: string , picture: string , name: string , token: string }`
    - **Response:** `{ message: User Updated , code: 200 , data: null }`

- **Get User Info**

    - **Method:** `GET`
    - **URL:** `/api/getdata`
    - **Request:** `{ email: string , token: string }`
    - **Response:** `{ message: User Data , code: 200 , data: User }`
  
- **Success Code**
  - **Code** `200`
  - **Message** `OK`
  - **Meaning** `Process Succeded`

- **Error Codes**
  - **Code** `300`
  - **Message** `Access Denied`
  - **Meaning** `Wrong Token`
  - **Code** `410`
  - **Message** `Couldn’t Login User Not Found`
  - **Meaning** `User Not Found`
  - **Code** `411`
  - **Message** `Couldn’t Signup User may Exists`
  - **Meaning** `User may Exists`
  - **Code** `412`
  - **Message** `Couldn’t Update Logic Error`
  - **Meaning** `Logic Error`
  - **Code** `413`
  - **Message** `Couldn’t get user's data Logic Error`
  - **Meaning** `Logic Error`
  - **Code** `414`
  - **Message** `Couldn’t Delete User Logic Error`
  - **Meaning** `Logic Error`