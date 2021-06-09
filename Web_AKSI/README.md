**The purpose of this web is to allow instance's employees to checked the stored data of each patient from the database.** 

**The data include patient's registration ID, age, gender, symptomps, vaccine manufacturer, and the machine learning death prediction result.**

**Steps to Replicate:**
1. Set a random (or make a new) directory in your PC as the firebase project directory
2. Ensure Node.js is installed
3. Initialize firebase client with "npm install -g firebase-tools"
4. Execute "firebase login" and login with Google account
5. Through node.js command prompt initialize firebase (in the directory made in step 1) with "firebase init" and make sure to select hosting
6. Enter the folder for the target web deployment. The default is "public"
7. Replace all the content in the just created "public" directory with the file from this "Web_AKSI" directory
8. lastly deploy the web with "firebase deploy" (use "firebase deploy --except functions" if wanted to exclude function deployment)
