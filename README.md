# NiniPerfumes - Perfume Catalog App

<img src="https://github.com/belleb23/NiniPerfumes/assets/115180927/6038ed26-f1d3-43ad-b5eb-7eae270dab1a" alt="Gif App" width="300" height="700"/>

## Features

#### Common Features (Available for Customer and Administrator)

1. **Login and Registration Screen**
   - Allows new users to register on the application.
   - Existing users can log in using their credentials.

2. **User Authentication**
   - Guarantee of secure access to the application through credential authentication.

3. **Logout**
   - Authenticated users can exit the application at any time, ensuring data security and privacy.

4. **Perfume List**
   - Displays a complete list of perfumes registered in the application.
   - Each perfume is shown with details such as name, description and image.

#### Specific Features for Customers

1. **Favorite Perfumes**
   - Customers can mark perfumes as favorites for quick and easy access later.

2. **Perfume View**
   - Customers can view details of each perfume, including name, description, image and price.

3. **Access to the Catalog**
   - Navigation through the perfume catalog to explore different options available.

#### Specific Functionalities for Administrators

1. **Perfume Creation and Edition**
   - Administrators can add new perfumes to the catalog.
   - Allows editing of existing perfume information, such as name, description, image and price.

2. **Perfume Removal**
   - Administrators have the ability to remove perfumes from the catalog.

## Project technologies

- `Kotlin`: Main programming language used for application development.
- `Authentication flow with DataStore`: store primitive types via preferences, such as the ID of the authenticated user
- `Migration`: allow the App to evolve each time Room entities are modified, as they also modify the database schema
- `Coroutines and Flow`: used to communicate with Room and DataStore
- `StateFlow`: allow changing the Flow value outside the builder, for example, updating the value when collecting new values ​​from another Flow.
- `Activity base`: share common code between Activities, such as authentication code that allows access to the logged in user, log out of the App and check whether the user is logged in or not
- `Relationship in Room`: configure entity to identify which record it belongs to

## Installation Instructions

1. Clone this repository:
   ```sh
   git clone https://github.com/belleb23/NiniPerfumes.git
   
2. Open the project in Android Studio.

3. Sync the project with Gradle to download all necessary dependencies.

4. Run the application on an emulator or physical device.
