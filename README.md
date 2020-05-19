# ohjelmistoprojekti1_backend
Kysely API:n käyttöohjeet

General

Kysely tietokannassa on taulut kysely, kysymys, vaihtoehto, sessioid ja vastaus.

Taulujen sisältö: 

Kysely
•	kysely_id (long)
•	name (String)

Kysymys
•	kysymys_id (long)
•	kysymys (String)
•	pakollinen (boolean)
•	tyyppi (integer) (java-luokassa tyyppi tallennetaan enumina, minkä takia tyyppinä on integer)
•	kysely (kysely_id)

Vaihtoehto
•	vaihtoehto_id (long)
•	vaihtoehto (String)
•	kysymys (kysymys_id)

Sessioid
•	sessio_id (long)
•	kysely (kysely_id)

Vastaus
•	vastaus_id (long)
•	sessio (sessio_id)
•	vastaus (String)
•	kysymys (kysymys_id)


Kaikkien kyselyiden hakeminen
https://salenpalikatback.herokuapp.com/kyselyt

Metodi: GET

Endpoint palauttaa kaikki tietokannan kyselyt olioina ilman vastauksia ja sessioID:tä

 

Tietyn kyselyn hakeminen
https://salenpalikatback.herokuapp.com/kysely/{id}

Metodi: GET

Endpoint palauttaa tietyn kyselyn oliona ilman vastauksia ja sessioID:tä, {id} paikalle tulee kyselyn id.

Esim. https://salenpalikatback.herokuapp.com/kysely/1


Kaikkien kyselyiden hakeminen vastauksineen
https://salenpalikatback.herokuapp.com/kyselytadmin

Metodi: GET

Endpoint palauttaa kaikki teitokannan kyselyt olioina vastauksien ja sessioID:n kanssa.
 

Tietyn kyselyn hakeminen vastauksineen
https://salenpalikatback.herokuapp.com/kyselyadmin/{id}

Metodi: GET

Endpoint palauttaa tietyn kyselyn oliona vastauksien ja sessioID:n kanssa, {id} paikalle tulee kyselyn id

Esim. https://salenpalikatback.herokuapp.com/kyselyadmin/1


Vastausten lähettäminen
https://salenpalikatback.herokuapp.com/kyselyt

Metodi: POST

Endpointiin lähetetään kysely olio, joka sisältään kaikki kysymykset, joihin ollaan vastattu.

Esim. olio:

{
   "kysely_id":1,
   "kysymykset":[
      {
         "kysymys_id":2,
         "vastaus": [
             {
                 "vastaus": "Vastaus tähän",
             }
         ]
      },
      {
         "kysymys_id":3,
         "vastaus": [
             {
                 "vastaus": "Vastaus tähän",
             }
         ]
      }
   ]
}

Uuden kyselyn lisääminen
https://salenpalikatback.herokuapp.com/kysely

Metodi: POST

Endpointtiin lähetetään uusi kysely oliona. Kysymystyyppejä on 4, Radio, Teksti, Skaala ja Monivalinta. Kysymys olioita lähetettäessä tulee kysymyksen tyypin olla yksi näistä String muodossa. Radio- ja monivalintakysymyksissä kysymyksen vaihtoehtot atribuuttiin liästään vaihtoehdot listana, teksti- ja skaalakysymyksiin vaihtoehto atribuutissa lähetetään lista, jossa yksi tyhjä vaihtoehto. Kysymyksen pakollisuuden voi määritellä asettamalla pakollinen-atribuutti joko false (ei pakollinen) tai true (pakollinen).

Esimerkki olio:
{
    "name": "Uuden kyselyn nimi tähän",
    "kysymykset": [
        {
            "tyyppi": "Radio",
            "kysymys": "Ensimmäisen kysymyksen nimi tähän",
            "pakollinen": false,
            "vaihtoehdot": [
                {
                    "vaihtoehto": "Vaihtoehto 1 tähän"
                },
                {
                    "vaihtoehto": "Vaihtoehto 2 tähän"
                },
                {
                    "vaihtoehto": "Vaihtoehto 3 tähän"
                }
            ]
        },
        {
            "tyyppi": "Teksti",
            "kysymys": "Kysymyksen nimi tähän",
            "pakollinen": true,
            "vaihtoehdot": [
                {
                    "vaihtoehto": ""
                }
            ]
        }
    ]
}

Kyselyn muokkaaminen
https://salenpalikatback.herokuapp.com/kysely/{id}

Metodi: PUT

Endpointtiin palautetaan kysely olio, jossa uudet tiedot. Kaikki aikaisemmat vastaukset ja kysymykset  poistetaan muokkauksen yhteydessä. {id} paikalle muokattavan kyselyn id.

Esimerkkiolio:

{
    "name": "Uusi nimi kyselylle",
    "kysymykset": [
        {
            "tyyppi": "Radio",
            "kysymys": "Uusi kysymys",
            "pakollinen": false,
            "vaihtoehdot": [
                {
                    "vaihtoehto": "Vaihtoehto 1"
                },
                {
                    "vaihtoehto": " Vaihtoehto 2"
                },
                {
                    "vaihtoehto": " Vaihtoehto 3"
                }
            ],
            "vastaus": []
        },
        {
            "tyyppi": "Teksti",
            "kysymys": " Uusi kysymys ",
            "pakollinen": false,
            "vaihtoehdot": [
                {
                    "vaihtoehto": ""
                }
            ],
            "vastaus": []
        }
    ]
}


Kysymyksen poistaminen kyselystä
https://salenpalikatback.herokuapp.com/kysymys/{id}

Metodi: DELETE

Endpointissa {id} paikalle tulee kysymyksen id.

Esim.
https://salenpalikatback.herokuapp.com/kysymys/2
https://salenpalikatback.herokuapp.com/kysymys/3




Yhden uuden kysymyksen lisääminen kyselyyn
https://salenpalikatback.herokuapp.com/kysymys/{id}

Metodi: POST

Endpointissa lähetetään uusi kysymys oliona, {id} paikalle tulee kyselyn id, mihin ollaan lisäämässä kysymys.

Esimerkki olio:
{
            "tyyppi": "Teksti",
            "kysymys": "Uusi kysymys",
            "pakollinen": false,
            "vaihtoehdot": [
                {
                    "vaihtoehto": ""
                }
            ],
            "vastaus": []
        }

Kysymyksen muokkaaminen
https://salenpalikatback.herokuapp.com/kysymys/{id}

Metodi: PUT

Endpointtiin lähetetään muokattu kysymys oliona. {id} paikalle tulee muokattavan kysymyksen id.

Esimerkki olio:
{
            "tyyppi": "Teksti",
            "kysymys": "Uusi kysymys",
            "pakollinen": false,
            "vaihtoehdot": [
                {
                    "vaihtoehto": ""
                }
            ],
            "vastaus": []
        }


