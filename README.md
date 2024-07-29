# Medicine Tracker App

The aim of this project is to create an easy way to record medication / symptoms such that the user's general progress can be tracked. 

Note: this is a prototype only so do not enter real, personal data.

## How To Use

### Setting Up

1. Download Android Studio on a computer
2. Download the zip file of this application's code 
3. Open the application in Android Studio
4. Connect computer to android phone
5. Troubleshoot connections and make sure phone is on developer mode
6. Run (press green arrow)

### Using Application

The app opens on the current month. To navigate, there is a "PREV" and "NEXT" button at the top. Pressing "PREV" for a short amount of time moves you to the previous month and holding it down moves you to the previous year ("NEXT" works similarly). The current month and year are displayed at the top. By clicking one of the 8 colour checkboxes and the digit display of a day, a bar of that colour can be added or removed for that day. This would be used to record a symptom or medication for that day. Multiple colours can be selected at once.

The meaning of each colour can be edited by the user and the "UPDATE" button at the bottom pressed to save their input. The checkboxes at the bottom after "Show" indicate which colours will be displayed on the calender. Clicking "UPDATE" will implement these changes. This can be useful if you wish to only focus on one particular medication or symptom.

"CURRENT DATE" moves the user back to the current date on the calendar. "YEAR VIEW" allows the user to see an overview of each year per page ("PREV" transports them to either the previous year or ten years ago). The contents cannot be changed, so it is mainly to see the user's progress over that year. Once again, they can choose which colours are shown.

The user's data is stored locally on the phone using a SQLite database.


