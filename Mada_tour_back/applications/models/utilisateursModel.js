var mongoose = require('mongoose');
var Schema   = mongoose.Schema;

var utilisateursSchema = new Schema({
    "nom": String,
    "prenom":String,
    "mail": String,
    "password":String,
    "date_naissance":Date,
    "date_inscription": Date
});

const utilisateursModel = mongoose.model("utilisateurs",utilisateursSchema,"utilisateurs");

module.exports = utilisateursModel;
