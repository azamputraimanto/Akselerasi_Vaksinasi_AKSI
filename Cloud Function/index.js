const functions = require("firebase-functions");
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);
const {google} = require("googleapis");

exports.predictKIPI = functions.database.ref('patients/{regId}').onCreate(event => {
    const regisId = event.key;
    const value = event.val();

    //Auth
    google.auth.getApplicationDefault(function(err, authClient) {
        if (err) {
            return cb(err);
        }

        if (authClient.createScopedRequired && authClient.createScopeRequired()) {
            authClient = authClient.createScoped([
                'https://www.googleapis.com/auth/cloud-platform'
            ]);
        }

        var ml = google.ml({
            version: 'AKSI_2',
            auth: authClient
        });

        const model = "Model_AKSI_V2";
        ml.projects.predict({
            name: `projects/capstone-project-312804/models/${model}`,
            resource: {
                name: `projects/capstone-project-312804/models/${model}`,
                instances: [`${value.SYMPTOM0},${value.AGE_YRS},${value.SEX},${value.VAX_MANU}`]
            }
        , function (err, result){
            if (err) {
                console.error('ERROR IN', err)
            }
            if (result.predictions[0].predicted) {
                admin.database().ref(`/patients/${regisId}`).set({
                    prediction: result.predictions[0].predicted
                });
            }}
        });
    });
});
