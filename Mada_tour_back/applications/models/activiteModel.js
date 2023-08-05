const mongoose = require("mongoose");
const { Schema } = mongoose;
// Sous-schéma pour stocker les traductions dans différentes langues
const traductionSchema = new Schema({
    langue: { type: String, required: true },//"EN"/"FR"
    value: { type: String, required: true },
});
const ActiviteSchema = new mongoose.Schema({
    nom: [traductionSchema],
    type_activite: {
        type: String
    },
    region:{
        type: String
    },
    images_url: {
        type: String
    },
    description: [traductionSchema],
    "tarifA":Number,
    "tarifE":Number, // A  : Adulte et E :Enfant
    "horaires":String, //au format HH:MM-HH:MM
    "dayOff":String // au format ex ferme le Dimanche et Mardi : data = "1-3" 
});

const activiteModel = mongoose.model("Activite", ActiviteSchema,"activite");
module.exports = activiteModel;