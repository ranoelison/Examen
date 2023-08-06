const mongoose = require("mongoose");

const TypeActiviteSchema = new mongoose.Schema({
    libelle: {
        type: String,
        required: true,
    },
    description: {
        type: String,
        required: true,
    },
});

module.exports = mongoose.model("type_activite", TypeActiviteSchema);
