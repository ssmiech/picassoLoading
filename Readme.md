# Image loading test 

Three modes are available: 

1. Loading two High resolution Images concurrently 
2. Loading one High resolution Image
3. Loading one Low resolution Image and one High resolution Image concurrently. 


### Results(s)
Loading 100 document pages into an ImageView 
No cache

|           | One HighRes Image | Two concurrent HighRes Images | Two concurrent Low and HighRes Images |
| ----------|:-----------------:| :----------------------------:| :------------------------------------:|
| WiFi      | 55.72             | 57.76                         | 59.63                                 |
| LTE       | 45.47             | 46.29                         | 43.13                                 |
| 2g        | 529.54            | 521.51                        | 526.75                                |
