# Tourist Guide Application

## Overview
This project is a comprehensive Tourist Guide web application designed to provide information about tourist destinations, activities, and related articles. It allows users to browse destinations, read articles, and leave comments, while also providing an administrative interface for content management.

## Key Features
- **User Management**: Registration, login, and role-based access control (admin/editor)
- **Destination Management**: Add, edit, and delete tourist destinations
- **Article Management**: Create, edit, and publish articles about destinations
- **Activity Tagging**: Tag articles with relevant activities (e.g., skiing, hiking, nightlife)
- **Comments System**: Allow visitors to comment on articles
- **View Tracking**: Track article popularity through view counts
- **Content Filtering**: Browse articles by recency, popularity, or destination

## Technology Stack

### Backend
- **Java** with **JAX-RS** framework for RESTful API development
- **Jersey** for implementing the JAX-RS specification
- **MySQL** database for data storage
- **JWT** (JSON Web Tokens) for authentication
- **Maven** for dependency management and build
- **Jackson** for JSON processing
- **Lombok** for reducing boilerplate code

### Frontend
- **Vue.js 2** framework for building the user interface
- **Vuex** for state management
- **Vue Router** for client-side routing
- **Bootstrap Vue** for responsive UI components
- **Axios** for HTTP requests

### Database
- Relational database with tables for users, destinations, articles, activities, and comments
- Foreign key relationships to maintain data integrity

## Architecture
The application follows a standard client-server architecture:
- RESTful API backend that handles data processing and business logic
- Single-page frontend application that consumes the API
- Repository pattern for database interactions
- Service layer for business logic
- JWT-based authentication for secure API access

This Tourist Guide application allows tourists to discover interesting destinations, read about local attractions and activities, and share their experiences through comments, while providing content managers with tools to easily create and manage content.
