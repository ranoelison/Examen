const router = require("express").Router();
// const { getActivities } = require("../applications/controllers/Activite");
const { getActualite } = require("../applications/controllers/Actualite");

router.get("/", (req, res) => {
  res.send("Let's build a CRUD API!");
});
// router.get("/activity", getActivities);
router.get("/actu", getActualite);

module.exports = router;
