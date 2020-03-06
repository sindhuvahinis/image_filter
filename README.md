# Image Filter
# Course CS 5010

#### Team - Sindhu Vahini Somasundaram & Darshan Narayanaswamy

## How to use the program

* AbstractController class consists of **main** method, which plugs in user data directly to the
 models.
* Use the jar to execute the project, please pass -interactive or -script <file-path> as an argument.

Example:

java -jar ImageFilter.jar -script "filepath/of/the/commands/file.txt"

java -jar ImageFilter.jar -interactive

### Instructions for interactive environment
* The main screen has four main menus. Filter, Transform, Patterns and Flags.
* Under Filter menu, three submenus such as Blur, Sharpen and Mosaic operations are available.
* Under Transform menu, Greyscale, Sepia, and Dither color transformations options are available. 
* Under Pattern menu, Rainbow and Checkerboard images can be generated. Choosing the option may prompt the user to enter the respective dimensions. Upon entering, the respective image will be generated for the specified dimension.
* Under Flag menu, France, Greece and Switzerland flags of the user specified size can be generated.
* In the bottom, there are two options available to load and save the images.  

### Instructions for script
#### Applying filters and transformations on input images for scripts
* Use **_load "imagepath"_** to load the image to the project.
* Use commands like **_blur_** or **_sepia_** to apply the respective filter on the loaded image.
* Use **_save "new-image-path"_** to save the modified image.

#### Computer generated images
* Rainbow pattern construction requires the user to pass **width/height** as 1st parameter, **other**
 dimension as the 2nd parameter and **orientation** as the 3rd parameter 
* Checkerboard pattern construction requires the user to pass **size** of the square as an input
 parameter.
* All flags require the user to pass the **width** of the flag as an input parameter.

#### Available commands usage instructions

##### Load image
load "src/path/of/the/image.format"

#### Save image
save "dest/path/of/the/image.format"

#### Blur image
blur

#### Sharpen image
sharpen

#### Mosaic image
mosaic seeds=1000

#### GreyScale image
greyscale

#### SepiaTone image
sepia

#### Dither image
dither

#### Checkerboard pattern
checkerboard size=10

#### Rainbow 
* rainbow side=50 other=700 orientation=horizontal
* rainbow side=50 other=700 orientation=vertical

#### Flags
* **France** - france width=300
* **Greece** - greece width=270
* **Switzerland** - switzerland width=320

## Design Changes
* There is no major design changes compared to the previous implementation, except for the driver
 class which was replaced by a command-controller to take the inputs as batch commands.
* Dither class and Mosaic class inherits the previously defined interface ImageModel and implements its apply
() function on the input image. 

## Completed features
* Image can be loaded and saved.
* Image can be blurred, sharpened, and converted to mosaic filter.
* Image can be color transformed to greyscale, sepia-tone, and dither.
* Patterns like Checkerboard and Rainbow can be generated.
* Flags of the countries France, Greece, and Switzerland can be generated.  

## Citations

#### Photo by Brooke Lark on Unsplash
![Plate][PLATE]

#### Photo by Matt Jones on Unsplash
![Van][VAN] 

#### Photo by Nadine Primeau on Unsplash
![Food][FOOD]

####Icons from [Remix Icon](https://remixicon.com/)
![blur] ![sepia] ![MOSAIC] ![SHARPEN] ![DITHER] ![FLAGS] ![RAINBOW] ![CHECKERBOARD] ![GREYSCALE]


## Licences
[Unsplash](https://unsplash.com/license)

All photos published on Unsplash can be used for free. You can use them for commercial and noncommercial purposes. You do not need to ask permission from or provide credit to the photographer or Unsplash, although it is appreciated when possible.

More precisely, Unsplash grants you an irrevocable, nonexclusive, worldwide copyright license to download, copy, modify, distribute, perform, and use photos from Unsplash for free, including for commercial purposes, without permission from or attributing the photographer or Unsplash. This license does not include the right to compile photos from Unsplash to replicate a similar or competing service.

[Remix Icon](https://github.com/Remix-Design/remixicon#license)

Remix Icon is licensed under the Apache License Version 2.0. Feel free to use these icons in your products and distribute them. We would be very grateful if you mention "Remix Icon" in your product info, but it's not required. The only thing we ask is that these icons are not for sale.




[VAN]: res/input/van.jpg

[FOOD]: res/input/food.jpg

[PLATE]: res/input/plate.jpg

[BLUR]: res/assets/blur.png

[SEPIA]: res/assets/sepia.png

[MOSAIC]: res/assets/mosaic.png

[SHARPEN]: res/assets/sharpen.png

[DITHER]: res/assets/dither.png

[FLAGS]: res/assets/flags.png

[RAINBOW]: res/assets/rainbow.png

[CHECKERBOARD]: res/assets/checkerboard.png

[GREYSCALE]: res/assets/greyscale.png
