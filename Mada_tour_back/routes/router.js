const router = require("express").Router();
// const { getActivities } = require("../applications/controllers/Activite");
const { getActualite } = require("../applications/controllers/Actualite");
const { getActivities } = require('../applications/controllers/activiteController');

// router.get("/activity", getActivities);
router.get("/actu", getActualite);
router.get("/activities/:filter?", getActivities);

module.exports = router;
