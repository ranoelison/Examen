const ActiviteModel = require('../models/activiteModel.js');
const Activite = require('../classes/Activite.js');
const Avis = require('../classes/Avis.js');

const sha1 = require('sha1');
const { last } = require('lodash');
const { now } = require('lodash');
const activiteModel = require('../models/activiteModel.js');

/**
 * activiteController.js
 *
 * @description :: Server-side logic for managing  activite & avis.
 * 
 */
module.exports = {
    getActiviteById: async function (req, res) {
        try {
            const activite_id = req.params.activite_id;
            const query = {
                _id: activite_id
            };
            console.log(query);
            const activiteFiche = await ActiviteModel.findOne(query).exec();
            if (!activiteFiche) {
                const respbody = {
                    status: "404",
                    message: "Fiche Activite-Not Found",
                    data: null
                };
                res.status(404);
                return res.json(respbody);
            }
            const respbody = {
                status: "200",
                message: "OK",
                data: activiteFiche
            };
            res.status(200);
            console.log(respbody);
            return res.json(respbody);
    
        } catch (error) {
            console.error(error);
            const body = {
                status: "500",
                message: error.message,
                data: ""
            };
            res.status(500);
            return res.json(body);
        }
    },
    getListAvisByActivite : async function(req,res){
        try {
           
            const activite_id = req.params.activite_id;
            // || req.body.activite_id;

            const avis = await Avis.listAvisByActivite(activite_id);
            const response = {
                status: "200",
                message: "Liste des avis pour l'activité",
                data: avis
            };
            res.json(response);
            res.status(200);
        } catch (error) {
            console.error(error);
            const body = {
                status: "500",
                message: error.message,
                data: ""
            };
            res.json(body);
            res.status(500);
        }
    },
    addAvis : async function(req,res){
        const {utilisateurs_id,activite_id,note,contenu} = req.body
        try{
            const newAvis = new Avis(utilisateurs_id,activite_id,note,contenu,new Date());
           
            newAvis.insert().then(resp => {
                console.log(newAvis);
                const response ={
                    status:"200",
                    message:"Ajout OK",
                    data:  newAvis
                };
                res.json(response);
                res.status(200);
            }).catch(error => {
                console.log(error);
                const body = {
                    status:"500",
                    message:error,
                    data : error
                };
                console.log(body);
              
                res.json(body);
                res.status(500);
              
            });
            
        }catch (error) {
            const body = {
                status:"500",
                message:error,
                data : ""
            };
            console.log(body);
          
            res.json(body);
            res.status(500);
        }
    },
    addActivite : async function(req,res){
        const {
                nom,type_activite,region,images_url,description,
                tarifA,tarifE,horaires,dayOff
            } = req.body
        try{
            const newActivite = new Activite(
                                                nom,type_activite,region,images_url,
                                                description,tarifA,tarifE,horaires,dayOff
                                            );
            newActivite.mode = "update";
            newActivite.insert().then(resp => {
               
                const response ={
                    status:"200",
                    message:"OK ",
                    data:  {
                        activite: newActivite
                    }
                };
                res.json(response);
               
                res.status(200);
                console.log(response);
            }).catch(error => {
                console.log(error);
                const body = {
                    status:"500",
                    message:error,
                    data : error
                };
                console.log(body);
              
                res.json(body);
                res.status(500);
              
            });
            
        }catch (error) {
            const body = {
                status:"500",
                message:error,
                data : ""
            };
            console.log(body);
          
            res.json(body);
            res.status(500);
        }
    },
    // Liste activites
    getActivities : async function(req, res) {
        try{
            console.log('getFiltered Activities');
            if(req.params.filter){
                let data = await ActiviteModel.find({
                    "$or": [
                        {region: { "$regex": req.params.filter,  "$options": "i" }},
                        {type_activite: { "$regex": req.params.filter,  "$options": "i" }},
                    ]
                });
                res.status(200);
                res.message = "OK";
                res.json(data);
            }
            else{
                let data = await ActiviteModel.find();
                res.status(200);
                res.message = "OK";
                res.json(data);
            }
        }
        catch(err){
            console.log(err);
            res.status(500);
            res.send(err);
        }
    },
};