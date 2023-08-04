const mongoose = require("mongoose");

const ActiviteSchema = new mongoose.Schema({
    nom: {
        type: String,
        required: true,
    },
    type_activite: {
        type: String,
        required: true,
    },
    region: {
        type: String,
        required: true,
    },
    images_url: {
        type: String
    },
    description: {
        type: String,
        required: true,
    },
    "tarifA":Double,
    "tarifE":Double, // A  : Adulte et E :Enfant
    "horaires":String, //au format HH:MM-HH:MM
    "dayOff":String // au format ex ferme le Dimanche et Mardi : data = "0-2" 
});

const activiteModel = mongoose.model("Activite", ActiviteSchema,"activite");
module.exports = activiteModel;