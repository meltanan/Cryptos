1. Description: this is an Android mobile application that contains two views: one to show price of Bitcoin and time that is associated with it as it updates the price every five minues,
and the other view is to show all other cryptos details such as name, rank, status if it acitve or not, and how many watchers. 
2. Libraries: Retrofit2 
3. Knows bugs: items disappear in recyclerview after scrolling 
4. Design and architecture: this app uses MVVM design pattern as it uses viewModel to hold ui data and business logic, repository to communicate between viewModel and API, and API which uses REST API to get data.
The app uses sharedPreferences to save user prefered currency selection. 