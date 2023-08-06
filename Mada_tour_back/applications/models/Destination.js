const mongoose = require("mongoose");

const DestinationSchema = new mongoose.Schema({

    _id : {
        type: String,
    },
    nom: {
        type: String,
        required: true,
    },
    type: {
        type: String,
        required: true,
    },
    region: {
        type: String,
        required: true,
    },
    img_url: {
        type: String
    },

});

module.exports = mongoose.model("Destination", DestinationSchema,"actualite_destinations");
