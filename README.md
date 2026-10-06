# Automotive Equipment Store Management System

A Java client–server desktop information system for managing the work of an automotive equipment store.

## Features
- User login
- Customer management
- Automotive equipment management
- Order creation, search, update and deletion
- Order item management
- MySQL persistence
- Client–server communication
- System-operation architecture

## Technologies
**Java · Swing · MySQL · SQL · OOP · Client–Server Architecture · UML · NetBeans**

## Project structure
- `AutoOpremaKlijent` — Swing client application
- `AutoOpremaServer` — server, database broker and system operations
- `AutoOpremaZajednicki` — shared domain and transfer objects
- `auto_oprema.sql` — database schema/data
- `auto_oprema.drawio` — project diagram

## UI
The Swing GUI uses the same visual identity as my portfolio: burgundy (`#741B3F`), cream (`#FBF7F5`) and white.

## Running locally
1. Import `auto_oprema.sql` into MySQL.
2. Open the three NetBeans projects.
3. Update `AutoOpremaServer/dbconfig.properties` with your local database credentials.
4. Build `AutoOpremaZajednicki`.
5. Start the server application.
6. Start the client application.

> This repository is a cleaned portfolio version of an academic project. Build artifacts, NetBeans private settings and study notes were intentionally excluded.
