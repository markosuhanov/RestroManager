# Restaurant Management System - RestroManager

**Course:** *Advanced Web Technologies*  
**University:** _Faculty of Technical Sciences, Novi Sad_  
**Field of Study:** **Software Engineering and Information Technologies**  
**Project:** *Restaurant Management System*  
**Student:** __Marko Suhanov__  

**Technologies:** Spring Boot & Angular  
**Database:** MySQL
**Frontend app:** https://github.com/markosuhanov/restroManager_FE




## Purpose
The project aims to implement a web application serving as a centralized information system for a restaurant. Accessible by restaurant staff and an administrator for technical support, the system's primary purpose is to streamline order processing, menu updates, report generation, and employee management.

## User Types
The system distinguishes between various user roles:
- **Waiter**: Creates and serves orders.
- **Chef**: Prepares dishes according to orders.
- **Bartender**: Mixes drinks according to orders.
- **Floor Manager**: Creates drink menus.
- **Head Chef**: Creates the menu.
- **Manager**: Generates reports.
- **Director**: Hires new staff.
- **Administrator (Technical Support)**: Handles restaurant layout visualization.

## Functional Requirements

**1. Login and Logout**
- Users log in using assigned usernames and passwords.
- Successful login leads to the user's profile page, where they can log out.
- Multiple waiters can be logged in simultaneously on the same device.

**2. Switching Between Logged-in Waiters**
- Waiters can efficiently switch accounts by selecting their username from a list.
- Switching logs out the current user and logs in the new one if the correct password is entered.

**3. Order Creation**
- Waiters can create orders by selecting an available table and adding items from the menu.
- Items are filterable by category and have descriptions and images for easy selection.
- After confirming the order, it's sent to the kitchen/bar for preparation.

**4. Order Modification**
- Similar to order creation, modification allows adding, removing, or editing items on existing orders.
- System notifies kitchen/bar staff of any changes made.

**5. Order Deletion**
- Waiters can delete orders in case customers leave or for other unforeseen circumstances.
- Deleting frees up the table for new orders but keeps a record for reporting purposes.

**6. Serving Food and Drinks**
- Waiters physically serve items for active orders.
- Items are marked as served only when all components of the order are ready.
- After serving, the order can be finalized.

**7. Finalizing Orders**
- Finalizing an order calculates the bill, including a 15% tip.
- After closing the bill, the table becomes available for new orders, and the completed order is logically removed from the system.

**8. Restaurant Layout Management**
- Administrators assist in creating the restaurant layout, including table positions, sizes, and shapes.
- Layout adjustments are made via drag-and-drop functionality, and changes are saved for waiter reference.

**9. Order Pickup**
- Chefs and bartenders can mark items as prepared, updating their status and notifying other staff.
- Waiters receive notifications when items are ready for pickup.

## Non-functional Requirements
- **Server Platforms**: Utilize Spring Boot framework with Java implementation.
- **Client Platforms**: Angular framework for client-side applications, utilizing HTML, CSS, and TypeScript.
- **DevOps Flow**: Implement robust development and deployment practices.
- **System Security**: Ensure the system's security measures.
- **System Robustness**: Design the system to be resilient to failures.

This system ensures real-time notification delivery and maintains open communication channels at all times.
