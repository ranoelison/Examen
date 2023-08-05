const util = require("../helpers/util.js");
//const UtilisateursModel = require('../models/utilisateursModel.js');
const ActiviteModel = require('../models/activiteModel.js');
const sha1 = require('sha1');
const mongoose = require('mongoose');

class Activite {
    _id;
    _nom;
    _type_activite;
    _region;
    _images_url;
    _description;
    _tarifA;
    _tarifE;
    _horaires;
    _dayOff;
    _mode;
    
    constructor(nom, type_activite, region, images_url, description, tarifA, tarifE, horaires, dayOff) {
        this._nom = nom;
        this._type_activite = type_activite;
        this._region = region;
        this._images_url = images_url;
        this._description = description;
        this._tarifA = tarifA;
        this._tarifE = tarifE;
        this._horaires = horaires;
        this._dayOff = dayOff;
    }

    set _dayOff(value){
        this.__dayOff = value;
    }

    get _dayOff(){
        return this.__dayOff;
    }
    set horaires(value){
        this._horaires = value;
    }

    get horaires(){
        return this._horaires;
    }
    set tarifE(value){
        this._tarifE = value;
    }

    get tarifE(){
        return this._tarifE;
    }
    set tarifA(value){
        this._tarifA = value;
    }

    get tarifA(){
        return this._tarifA;
    }
    set mode(value){
        this._mode = value;
    }

    get mode(){
        return this._mode;
    }
    set type_activite(value) {
        if (util.isBlank(value)) {
            throw 'Champ `type_activite` vide.';
        }
        this._type_activite = value;
    }

    get type_activite() {
        return this._type_activite;
    }

    set region(value) {
        if (util.isBlank(value)) {
            throw 'Champ `region` vide.';
        }
        this._region = value;
    }

    get region() {
        return this._region;
    }

    set images_url(value) {
        if (util.isBlank(value)) {
            throw 'Champ `images_url` vide.';
        }
        this._images_url = value;
    }

    get images_url() {
        return this._images_url;
    }

    set description(value) {
        if (util.isBlank(value)) {
            throw 'Champ `description` vide.';
        }
        this._description = value;
    }

    get description() {
        return this._description;
    }
    ///deprecated by me
    static async getActivite(activite_id){
        try{
           /* console.log("--------Obj Id--------------");
            const objectId = new mongoose.Types.ObjectId(activite_id);
            const oneActivite = await ActiviteModel.findById(objectId).exec();*/
            const query = {
                _id: activite_id
            };
            const oneActivite =  await ActiviteModel.findOne(query);
            console.log("--------ACTIVITE--------------");
            console.log(oneActivite);
            return oneActivite;
        }catch(error){
            throw error;
        }
    }
    async insert(){
       const newActivite = new ActiviteModel({
        nom:this._nom,
        type_activite:this._type_activite,
        region:this._region,
        images_url :this._images_url,
        description : this._description,
        tarifA: this._tarifA,
        tarifE: this._tarifE,
        horaires: this._horaires,
        dayOff: this._dayOff
       });
        

        return  await newActivite.save().then().catch(err => { throw err });
    }
}
module.exports = Activite;