# Space Explorer App

## Description
Space Explorer is a mobile application that allows users to explore and search for news articles from various sources. With a user-friendly interface, users can easily find articles based on their interests and keep track of their recent searches.

## Features
- **Article List**: Display a list of news articles fetched from an API.
- **Search Articles**: Users can search for articles by entering a title in the search bar.
- **Filter by Category**: Filter articles by news source using a dropdown menu.
- **Article Detail View**: View detailed information about selected articles, including images, publication time, and summaries.
- **Recent Searches**: Access and view the history of recent searches made by the user.
- **Swipe to Refresh**: Refresh the article list to get the latest news by swiping down.
- **Responsive Design**: Utilize RecyclerView for a responsive and smooth user experience.
- **Delete Recent Searches**: Option to clear the entire recent search history with confirmation dialog.

## Architecture
The application follows the MVVM (Model-View-ViewModel) architecture pattern, which helps in separating the user interface logic from the business logic, ensuring a clean and maintainable codebase. Key components include:
- **View**: Activities and Fragments displaying UI elements.
- **ViewModel**: Holds and manages UI-related data in a lifecycle-conscious way.
- **Model**: Represents the data layer, including API services and local database.
- **Repository**: Mediates between the data sources (API and local database) and the ViewModel.

## Links
- **Demo Video**: [Watch on YouTube](https://youtu.be/vwiLhgcFNBw)
- **Download App**: [Download from Google Drive](https://drive.google.com/file/d/1ae6pkDtuKsLCol7aJg0VEUHAuG8RVe7-/view?usp=sharing)
