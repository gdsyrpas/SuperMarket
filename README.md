# 🛒 SuperMarket Management Project (Java)

## Project Overview

The **SuperMarket Management Project** is a Java application designed to simulate or manage core supermarket operations, such as inventory control, sales transaction processing, and receipt calculation.

The implementation follows **Object-Oriented Programming (OOP)** principles and is structured to maintain clear separation of concerns, prioritizing code quality and testability.

---

## 🛠️ Tech Stack and Tools

* **Programming Language:** Java (Version 11+ is recommended)
* **Development Environment (IDE):** Primarily developed using IntelliJ IDEA
* **Build System (Implied):** Standard Java/Module structure (or potentially Maven/Gradle, if config files are added later)
* **Unit Testing Frameworks:**
    * JUnit 4
    * JUnit 5.8.1

---

## 🚀 Getting Started

Follow these steps to clone, set up, and run the project on your local machine.

### 1. Clone the Repository

Open your terminal or Git Bash and execute the following command:

```bash
git clone [YOUR REPO URL HERE]
cd SuperMarket

2. Import into IDE

Import the SuperMarket folder as a project into your IDE of choice (e.g., IntelliJ IDEA, Eclipse):

    Select File -> Open and navigate to the project folder.

    The IDE should automatically recognize the src folder as the project's Source Root.

3. Running the Application

    Locate the main execution file (typically Main.java or SuperMarketApp.java) inside the src folder.

    Right-click the main file and select Run '...' or use the IDE's run button (usually a green play icon).

4. Running Unit Tests (Highly Recommended)

Given the setup with JUnit 4 and 5, running the tests is a key step:

    In your IDE, navigate to the folder containing the test classes (typically within src/test/).

    Right-click on the test folder or a specific Test Class and select Run Tests.

    All unit tests are expected to pass successfully (Green).

📂 Project Structure

SuperMarket/
├── src/                # All Java Source Code
│   ├── main/           # Application Code
│   └── test/           # Unit Test Files (JUnit)
├── .gitignore          # Git exclusion rules for build artifacts and IDE files
└── README.md           # This documentation file
