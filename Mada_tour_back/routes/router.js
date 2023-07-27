const router = require("express").Router();
const { getActivities, createTest } = require("../applications/controllers/Activite");

router.get("/", (req, res) => {
  res.send("Let's build a CRUD API!");
});

router.get("/activity", getActivities);

router.post("/test",createTest);

module.exports = router;
