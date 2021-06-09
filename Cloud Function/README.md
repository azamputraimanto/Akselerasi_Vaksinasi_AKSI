**The purpose of this function is to trigger the deployed ML Model to predict the KIPI of a patients when a new patients data (features) are inputted in the real time database**

**Steps to Replicate:**

1. Set a random (or make a new) directory in your PC as the firebase project directory.
2. Ensure Node.js is installed
3. Initialize firebase client with "npm install -g firebase-tools"
4. Execute "firebase login" and login with Google account
5. Through node.js command prompt initialize firebase (in the directory made in step 1) with "firebase init" and make sure to select functions
6. Install Google API Packages with "npm i googleapis"
7. Copy the content of this index.js file to your local firebase directory index.js
8. Set the content of the index.js to suits your specifications (Database reference, ML model & versions)
9. Deploy the cloud function with "firebase deploy --only functions"

_**Note:** The cloud function is still an unfinished product, due to some error:_
![image](https://user-images.githubusercontent.com/74135059/121293195-6a4f2500-c915-11eb-874e-a261c053bac9.png)
