# PostureAnnotationTool

VERSION=0.9.0

Java app used to create posture annotations used in posture assessment.

### How to use

1. Open directory with images (via 'Open Images' button).
2. Click on button 'Select person' and draw rectangle.
3. Select posture type (front / back / right / left).
4. Click on button 'Select points' to select specific points on image.
Which point to select will be indicated by printing its name (polish names :poland:) and showing helper image.
5. After all points are selected click on button 'Save labels' to save annotation to file.
Annotations will be saved to file with the same name as an image in directory 'posture_type'/labels.
After saving annotations image will be moved to directory 'posture_type'/images.

If you do not want to annotate image you can skip it by clicking button 'Next image'.

### Sample annotation file

Annotations are saved in csv file with structure:

> image_name.extension

> person_number; x1; y1; x2; y2 (if more people on image to annotate plus persons coordinates - top left point and bottom right point)

> person_posture_type (front/back/left/right)

> number_of_points_annotated_to_this_person

> point_name_1; x; y  (name of point to annotation plus its coordinates in image)

> point_name_2; x; y

> ... (the same for all points)

