var mongoose = require('mongoose');
var Schema   = mongoose.Schema;

var avisSchema = new Schema({
    utilisateurs_id : String,
    activite_id : String,
    note : Number,//entier note / 0<note<5
    contenu : String,
    date_submit : Date
});

const avisModel = mongoose.model("avis",avisSchema,"avis");

module.exports = avisModel;
