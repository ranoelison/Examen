const router = require("express").Router();
// const { getActivities } = require("../applications/controllers/Activite");
const { getActualite } = require("../applications/controllers/Actualite");


// router.get("/activity", getActivities);
router.get("/actu", getActualite);

module.exports = router;
