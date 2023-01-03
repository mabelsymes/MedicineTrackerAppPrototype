# MedicineTrackerApp
Second project using Java and Android Studio.

The aim of this project was to create a easy way to record medicine / symptoms such that the person's general progess can be tracked.

The app opens on the current month. To navigate, there is a "PREV" and "NEXT" button at the top. Pressing "PREV" for a short amount of time moves you to the previous month and holding it down moves you to the previous year ("NEXT" works similarly). The current month and year are displayed at the top. By clicking one of the 8 colour checkboxes and the digit display of a day, a bar of that colour can be added or removed for that day. Multiple colours can be selected at once.

The meaning of each colour can be edited by the user (e.g. one standing for a particular medication) and the "UPDATE" button at the bottom pressed to save their input. The checkboxes at the bottom after "Show" indicate which colours will be displayed on the calender. Clicking "UPDATE" will implement these changes. This can be useful if they wish to only focus on one particular medication or symptom.

"CURRENT DATE" moves the person back to the current date on the calendar. "YEAR VIEW" allows the person to see an overview of each year per page ("PREV" transports them to either the previous year or ten years ago). The contents cannot be changed, so it is mainly to see the person's progress over that year. Once again, they can choose which colours are shown.

The person's data is stored locally on the phone using a SQLite database.


