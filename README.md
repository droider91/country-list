# country-list
Sample application with clean architecture and MVVM in presentation layer.
Fetching all countries list with their flags.
The screen contains
**ViewPager**
**SearchView**
**RecyclerView**
All views are included in **Motion layout** which is great expansion of **ConstraintLayout**.
Using the *MotionLayout* we can create beautiful animation on the go.
All views are inline to sync update of scrolling on flags or click on the tabs or scrolling of the list.



#### This project is decoupled and have three layers:
1. **data**: It contains all the data accessing and manipulating components.
2. **ui**: View classes along with their corresponding ViewModel.
4. **domain**: Usecases and interfaces

![Home](https://github.com/droider91/country-list/blob/main/gifs/enter.jpg)
![Search](https://github.com/droider91/country-list/blob/main/gifs/search.jpg)
![Scrolling]([scrollup.jpg](https://github.com/droider91/country-list/blob/main/gifs/scrollup.jpg))
![Search ]([search2.jpg](https://github.com/droider91/country-list/blob/main/gifs/search2.jpg))
