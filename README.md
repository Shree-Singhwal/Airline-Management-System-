# ✈️ Airline Management System (AMS)

A complete, desktop-based **Airline Management System** developed in **Java (Swing & AWT)** with **MySQL Database** connectivity via **JDBC**.

---

## 📌 Features

- 🔐 **Admin Authentication:** Secure login screen with password verification.
- 📊 **Interactive Dashboard:** Modern graphical dashboard with navigation menus and quick-access cards.
- ✈️ **Flight Schedule & Search:** Real-time lookup of available flights, routes, sources, and destinations.
- 👤 **Passenger Registration:** Easy onboarding form for new customers (Name, Aadhar, Phone, Gender, Address).
- 🎟️ **Ticket Reservation:** Auto-generates unique **PNR** and **Ticket ID**, auto-fills passenger details by Aadhar, and books flight seats.
- 📋 **Journey Verification:** Track and view reservation details by entering PNR.
- ❌ **Ticket Cancellation:** Instant ticket cancellation with automated cancellation reference number generation.
- 🎫 **Official Boarding Pass:** Generates printable boarding passes complete with gate, seat, route, and simulated barcode.

---

## 🛠️ Tech Stack & Prerequisites

- **Language:** Java (JDK 17 or higher recommended)
- **GUI Toolkit:** Java Swing & AWT
- **Database:** MySQL Server 8.0+
- **Connectivity:** Java Database Connectivity (JDBC) via MySQL Connector/J
- **Build Tool / IDE:** VS Code (Visual Studio Code) or IntelliJ IDEA / Eclipse

---

## 📂 Project Directory Structure

```text
airline-management-system/
├── .vscode/
│   ├── launch.json              # 1-click launch configuration for VS Code
│   └── settings.json            # Java classpath & source settings
├── legacy-swing/
│   └── src/
│       └── airlinemanagementsystem/
│           ├── Conn.java        # JDBC MySQL Connection Manager
│           ├── Login.java       # Main Entrypoint / Login UI
│           ├── Home.java        # Main Dashboard
│           ├── FlightInfo.java  # Flight Schedule Table
│           ├── AddCustomer.java # Passenger Registration
│           ├── BookFlight.java  # Ticket Booking & PNR Generator
│           ├── JourneyDetails.java # PNR Lookup Table
│           ├── Cancel.java      # Cancellation Handler
│           └── BoardingPass.java# Boarding Pass Generator
├── database.sql                 # Complete MySQL schema and sample data
├── pom.xml                      # Maven configuration with MySQL driver
├── .gitignore                   # Ignores build artifacts and temp files
└── README.md                    # Project documentation
```

---

## 🗄️ Step 1: MySQL Database Setup

1. Open **MySQL Command Line Client** or **MySQL Workbench**.
2. Run the provided [`database.sql`](./database.sql) script:
   ```sql
   source /path/to/airline-management-system/database.sql;
   ```
   *(Or simply copy-paste the contents of `database.sql` into MySQL Workbench and click Execute).*

3. **Configure Database Credentials:**
   Open `legacy-swing/src/airlinemanagementsystem/Conn.java` and make sure your MySQL root password matches:
   ```java
   private static final String DB_USER = "root";
   private static final String DB_PASS = "your_mysql_password"; // <-- Update here if needed
   ```

### Default Login Credentials:
- **Username:** `admin`
- **Password:** `admin123`

---

## 💻 Step 2: How to Run in Visual Studio Code (VS Code)

1. **Install Java in VS Code:**
   - Install the **"Extension Pack for Java"** by Microsoft from the VS Code Extensions tab (`Ctrl + Shift + X`).

2. **Open the Project:**
   - In VS Code, go to `File` > `Open Folder...` and select the `airline-management-system` folder.

3. **Run the Application:**
   - Open `legacy-swing/src/airlinemanagementsystem/Login.java`.
   - Click the **Run** button at the top right, or press `F5` / `Ctrl + F5`.
   - Alternatively, open the **Run & Debug** tab (`Ctrl + Shift + D`) and select **"Launch Login (Airline System)"**.

---

## 🚀 Step 3: How to Publish / Push to GitHub

1. Open the integrated terminal in VS Code (`Ctrl + ~`) in the project root:
   ```bash
   # Initialize Git
   git init

   # Stage all files
   git add .

   # Commit changes
   git commit -m "Initial commit: Airline Management System with Swing and JDBC"
   ```

2. Create a new repository on [GitHub](https://github.com/new):
   - Repository Name: `airline-management-system`
   - Leave it **Public** (or **Private** as desired).
   - **Do NOT** check "Add a README" (we already created one).

3. Link the remote and push:
   ```bash
   git branch -M main
   git remote add origin https://github.com/<your-username>/airline-management-system.git
   git push -u origin main
   ```
