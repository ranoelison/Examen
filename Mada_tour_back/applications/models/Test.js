const mongoose = require("mongoose");

const TestSchema = new mongoose.Schema({
    nom: {
        type: String,
        required: true,
    }

});

module.exports = mongoose.model("Test", TestSchema,"test");
