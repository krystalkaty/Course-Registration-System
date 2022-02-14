# Course-Registration-System

Assignment for Data Structures (CSCI-UA 102) with Anasse Bari. 

This is a Course Registration System (CRS) that allows admins to modify data and students to manage their courses. 

Workflow: When the programs run, check if .ser file exists. If it exists, deserialize. If not, read the "MyUniversityCourses.csv" file and store course objects in an ArrayList. Ask users to login and display their repsective menu depending on whether the user is an Admin or Student. Admin can manage and modify course data, while students can only manage their own courses. Users can perform actions from the menu until they choose to exit program. Serialize file to update and save information and then exit program. 
