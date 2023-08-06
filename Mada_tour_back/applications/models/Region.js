const mongoose = require("mongoose");

const RegionSchema = new mongoose.Schema({
  nom: {
    type: String,
    required: true,
  },
  localisation: {
    type: String,
    required: true,
  },
  images_url: {
    type: String
  },
});

module.exports = mongoose.model("regions", RegionSchema);
