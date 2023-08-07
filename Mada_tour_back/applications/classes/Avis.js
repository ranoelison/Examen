const util = require("../helpers/util.js");
//const UtilisateursModel = require('../models/utilisateursModel.js');
const ActiviteModel = require('../models/activiteModel.js');
const AvisModel = require('../models/avisModel.js');
const UtilisateurModel = require('../models/utilisateursModel.js');

class Avis {
    _id;
    _utilisateurs_id;
    _activite_id;
    _note;
    _contenu;  
    _date_submit;
    constructor(/*id,*/utilisateurs_id, activite_id, note, contenu,date_submit) {
        this._utilisateurs_id = utilisateurs_id;
        this._activite_id = activite_id;
        this._note = note;
        this._contenu = contenu;
       // this._id = id;
        this._date_submit = date_submit;
    }
   /* constructor(utilisateurs_id, activite_id, note, contenu) {
        this._utilisateurs_id = utilisateurs_id;
        this._activite_id = activite_id;
        this._note = note;
        this._contenu = contenu;
    }*/

    // Méthode getter pour _id
    get id() {
        return this._id;
    }

    

    // Méthode getter pour _utilisateurs_id
    get utilisateurs_id() {
        return this._utilisateurs_id;
    }

    // Méthode setter pour _utilisateurs_id
    set utilisateurs_id(value) {
        this._utilisateurs_id = value;
    }
    get date_submit() {
        return this._date_submit;
    }

    // Méthode setter pour _date_submit
    set date_submit(value) {
        this._date_submit = value;
    }

    // Méthode getter pour _activite_id
    get activite_id() {
        return this._activite_id;
    }

    // Méthode setter pour _activite_id
    set activite_id(value) {
        this._activite_id = value;
    }

    // Méthode getter pour _note
    get note() {
        return this._note;
    }

    // Méthode setter pour _note
    set note(value) {
        if (typeof value !== 'number' || value < 0 || value > 5) {
            throw new Error('La note doit être un nombre entre 0 et 5.');
        }
        this._note = value;
    }

    // Méthode getter pour _contenu
    get contenu() {
        return this._contenu;
    }

    // Méthode setter pour _contenu
    set contenu(value) {
        this._contenu = value;
    } 
    async insert(){
        const newAvis = new AvisModel({
            utilisateurs_id : this._utilisateurs_id,
            activite_id : this._activite_id,
            note: this._note,
            contenu : this._contenu,
            date_submit : new Date()
        });
       
        return  await newAvis.save().then().catch(err => { throw err });
    }
    static async listAvisByActivite(activite_id) {
        try {
            const avis = await AvisModel.find({ activite_id: activite_id }).sort({ date_submit: -1 }).exec();
    
            // Obtenir tous les _id des utilisateurs pour lesquels nous devons récupérer les noms
            const utilisateursIds = avis.map((avisItem) => avisItem.utilisateurs_id);
    
            // Récupérer tous les utilisateurs en une seule requête
            const utilisateurs = await UtilisateurModel.find({ _id: { $in: utilisateursIds } });
    
            // Créer un dictionnaire pour associer l'_id de l'utilisateur à son nom complet
            const utilisateursMap = {};
            utilisateurs.forEach((utilisateur) => {
                utilisateursMap[utilisateur._id] = utilisateur.nom + ' ' + utilisateur.prenom;
            });
    
            // Mettre à jour l'attribut utilisateurs_id pour chaque avis en utilisant le dictionnaire créé
            avis.forEach((avisItem) => {
                avisItem.utilisateurs_id = utilisateursMap[avisItem.utilisateurs_id];
            });
    
            return avis;
        } catch (error) {
            throw error;
        }
    }
   /* static async listAvisByActivite(activite_id) {
        try {
            const avis = await AvisModel.find({ activite_id: activite_id }).sort({ date_submit: -1 }).exec();
    
            // Parcourir chaque avis pour mettre à jour l'attribut utilisateurs_id
            const avisWithUsernames = await Promise.all(avis.map(async (avisItem) => {
                const utilisateur = await UtilisateurModel.findOne({ _id: avisItem.utilisateurs_id });
                if (utilisateur) {
                    // Concaténer le nom et le prénom de l'utilisateur et mettre à jour l'attribut utilisateurs_id
                    avisItem.utilisateurs_id = utilisateur.nom + ' ' + utilisateur.prenom;
                }
                return avisItem;
            }));
    
            return avisWithUsernames;
        } catch (error) {
            throw error;
        }
    }
    */
    static async listAvisByActiviteRealData(activite_id) {
        try {
            const avis = await AvisModel.find({ activite_id: activite_id }).sort({date_submit:-1}).exec();
            return avis;
        } catch (error) {
            throw error;
        }
    }
}
module.exports = Avis;
