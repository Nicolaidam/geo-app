package dk.example.geoapp

import dk.example.geoapp.api.GeoApiClient
import dk.example.geoapp.model.GeoItemDto
import dk.example.geoapp.model.Response
import kotlinx.serialization.json.Json

class MockGeoApiClient() : GeoApiClient {
    val json: Json = Json { ignoreUnknownKeys = true }
    override suspend fun getGeoItems(): Response<List<GeoItemDto>> {
        return try {
            val dtoList: List<GeoItemDto> = json.decodeFromString(jsonString)
            Response.Success(dtoList)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}

val jsonString = """
    [
      {
        "id": "e90262ec-be85-11ed-b7a2-06206ad47459",
        "name": "Bindeballe Station",
        "description": "Toilet",
        "latitude": 55.656907595,
        "longitude": 9.260990189,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "22726ae2-69cd-11ea-8a39-062b96cbb5a0",
        "name": "Toilet i Kærhuset",
        "description": "Offentligt geo i Kærhuset.",
        "latitude": 55.702089329,
        "longitude": 9.435532651,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "ea342bf0-be85-11ed-b7a6-06206ad47459",
        "name": "Toilet i Kærhuset",
        "description": "Offentligt geo i Kærhuset.",
        "latitude": 55.702128334,
        "longitude": 9.435427834,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "227720e6-69cd-11ea-8a73-062b96cbb5a0",
        "name": "Albuen strand",
        "description": "Offentligt geo.",
        "latitude": 55.705055626,
        "longitude": 9.579894341,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "224ed384-69cd-11ea-887b-062b96cbb5a0",
        "name": "Offentligt geo Rådhustorvet",
        "description": "Offentligt geo Rådhustorvet. Åbent om dagen. Gratis.",
        "latitude": 55.707950103,
        "longitude": 9.53257585,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "2279265c-69cd-11ea-8a8d-062b96cbb5a0",
        "name": "Offentligt geo Foldegade",
        "description": "Offentligt geo Foldegade. Døgnåbent.",
        "latitude": 55.706229547,
        "longitude": 9.533201813,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "227fe6a4-69cd-11ea-8ad5-062b96cbb5a0",
        "name": "Offentlig geo v. Byparken/Musikteatret",
        "description": "Offentlig geo v. Byparken/Musikteatret. Døgnåbent.",
        "latitude": 55.710465637,
        "longitude": 9.531772801,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "224bb2da-69cd-11ea-8851-062b96cbb5a0",
        "name": "Offentlig geo v. Ved Anlæget",
        "description": "Offentlig geo v. Ved Anlæget. Døgnåbent.",
        "latitude": 55.711384215,
        "longitude": 9.532828355,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "2266e398-69cd-11ea-89a3-062b96cbb5a0",
        "name": "Offentligt geo Volmers Plads",
        "description": "Offentligt geo Volmers Plads. Døgnåbent.",
        "latitude": 55.709954643,
        "longitude": 9.536766304,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "5710121a-1163-11ec-b63b-0661a94bd4ab",
        "name": "Muldgeo ved Vilslev fiskeplatform",
        "description": "Muldgeo med håndspritsautomat og geopapir. 2 gange ugentligt rengørings geotet af kommunen.",
        "latitude": 55.394679867,
        "longitude": 8.712698684,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/a30ffb51-3139-4d6f-a2ce-476d73be8186.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/16cb7e88-cdd5-4303-a1eb-8170e068a9d1.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/17d884d6-da75-458c-96d6-97d39bb713fa.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/36da9488-f65d-4b5b-9987-3bd0f23258f1.jpg"
        ]
      },
      {
        "id": "5728a56e-1163-11ec-b7c5-0661a94bd4ab",
        "name": "Toilet",
        "description": "To geoter med vandhane",
        "latitude": 55.396689778,
        "longitude": 8.737780089,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "ac112892-95ad-11ed-a68d-06206ad47459",
        "name": "Toilet (47318) - Hærvejsudstilling i Bække",
        "description": null,
        "latitude": 55.570546247,
        "longitude": 9.137551768,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "3fc5e4e5-fecb-4578-80ca-23af4778a9f8",
        "name": "Hem Odde",
        "description": null,
        "latitude": 56.028737435,
        "longitude": 9.792066725,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/be7c3c44-b56e-40d9-baa5-57dd4905a412.jpg"
        ]
      },
      {
        "id": "ae728ab4-1444-4004-8c75-570d9fbce1e3",
        "name": "Ejer Bavnehøj",
        "description": null,
        "latitude": 55.977500649,
        "longitude": 9.830457449,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/c0b5e178-e9ca-4426-8dde-2e5050c82bd7.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/f76a7867-aab1-4b88-8f3d-0b86f5ab32db.jpg"
        ]
      },
      {
        "id": "ae109560-f6eb-44ac-88ca-f96b3241256a",
        "name": "Galten busstation",
        "description": null,
        "latitude": 56.154879671,
        "longitude": 9.905093026,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/6a29a2a5-9650-4068-aa82-53ead9595b14.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/daf36713-2ebf-4a02-86d8-9b10d1a2f07b.jpg"
        ]
      },
      {
        "id": "c86639d4-29a3-4395-9f44-e12d6d684fda",
        "name": "Skanderborg busstation",
        "description": null,
        "latitude": 56.035598471,
        "longitude": 9.929228056,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/d8ea95b3-122b-4fb5-9f7c-1144a10f422d.jpg"
        ]
      },
      {
        "id": "160435c0-07ed-4dd7-abb8-1b2defd84214",
        "name": "Bybadet",
        "description": null,
        "latitude": 56.03557321,
        "longitude": 9.934614574,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/6ff7afbb-5be2-49d5-8c40-04d9b1cece07.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/fcbddcb1-fd95-4f37-810d-6048ef6b83cb.jpg"
        ]
      },
      {
        "id": "13751a5e-453a-4aa4-a8ff-5e8ec03bc085",
        "name": "Rasses Skovpølser",
        "description": null,
        "latitude": 56.026642808,
        "longitude": 9.914615211,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/c1cd573c-c27f-4b22-b783-3dc44c723fca.jpg"
        ]
      },
      {
        "id": "39a2daec-5994-4f84-8cbb-4485917e3b6b",
        "name": "Stilling Søbad",
        "description": null,
        "latitude": 56.060686423,
        "longitude": 9.990860353,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/85293abe-1f12-43ff-a5e4-7aaff2130901.jpg"
        ]
      },
      {
        "id": "77fcddff-e9de-409b-953c-16e8ac85ab75",
        "name": "Knudhule",
        "description": null,
        "latitude": 56.103411014,
        "longitude": 9.784302898,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/3fd0f079-ca89-4947-9bf5-fa890bb05cff.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/e1545576-abe8-440f-94b3-c62aabb35e44.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/17091c3f-84a1-44f9-975b-e8bbed607914.jpg"
        ]
      },
      {
        "id": "47e83f39-a3c3-48e9-a55d-1491432f8a6b",
        "name": "Siimtoften",
        "description": null,
        "latitude": 56.093324066,
        "longitude": 9.753493648,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/a96c90fd-e6bf-4fe5-b6b3-03ca7f7a775d.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/82ae222f-5c66-40c3-9505-ca164683cf00.jpg"
        ]
      },
      {
        "id": "cb9d761d-cee8-4978-ba6d-c58223bf3055",
        "name": "Fuldbro Mølle",
        "description": null,
        "latitude": 56.024091488,
        "longitude": 9.834324371,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/ca7b6874-2072-4bd3-b676-794d018c7c3d.jpg"
        ]
      },
      {
        "id": "d43b7aee-3636-4c27-a0b0-082cd6f5eddd",
        "name": "Mindet",
        "description": null,
        "latitude": 56.038481262,
        "longitude": 9.929471644,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/3f2e09c1-7cc9-495b-97a6-006372d5b6e2.jpg"
        ]
      },
      {
        "id": "a9d6b487-a4fa-4035-9349-0998255ec720",
        "name": "Museumsøen",
        "description": "Museumsøen",
        "latitude": 55.650520952,
        "longitude": 12.078640931,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/ea68a811-26ae-4a35-bdeb-4e64887d5822.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/8d09d097-2154-420a-86dd-0416c7f3ecbd.jpg"
        ]
      },
      {
        "id": "da6db97e-79fe-11ec-afe7-062ad32497bd",
        "name": "Klinten Jyllinge",
        "description": "Toilet på Klinten",
        "latitude": 55.749027,
        "longitude": 12.102342,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "878b163a-660e-11ef-8f06-8fd48e579521",
        "name": "Sct. Hans Have, Gartnerhuset",
        "description": "Toilet beliggende i Gartnerhuset ved Sct. Hans Have.",
        "latitude": 55.655201111,
        "longitude": 12.063696384,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "d7c74c85-6beb-4348-90f7-d480912a39e3",
        "name": "Salvadparken",
        "description": "Salvadparken",
        "latitude": 55.721515414,
        "longitude": 12.112719121,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/7da38c58-4651-4279-a571-d7d8c872da9f.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/1bb6da08-1a60-45ae-9072-5a254bc7d7e2.jpg"
        ]
      },
      {
        "id": "da6d5088-79fe-11ec-afe1-062ad32497bd",
        "name": "Vikingeskibsmuseet i Roskilde",
        "description": "Museumsøen",
        "latitude": 55.650293,
        "longitude": 12.079532,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "79a9ab42-690d-11ef-a517-45b84c3513a0",
        "name": "P-plads ved Hedelands Veteranbane",
        "description": "P-plads ved Hedelands Veteranbane",
        "latitude": 55.647034888,
        "longitude": 12.18817234,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "aea8ca7e-980f-11ed-b647-06206ad47459",
        "name": "Toilet (32299)",
        "description": null,
        "latitude": 55.359705487,
        "longitude": 9.21654522,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "aea928d4-980f-11ed-b649-06206ad47459",
        "name": "Toilet (32300)",
        "description": null,
        "latitude": 55.359697776,
        "longitude": 9.216507629,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "aea956f6-980f-11ed-b64b-06206ad47459",
        "name": "Toilet (32301)",
        "description": null,
        "latitude": 55.359651998,
        "longitude": 9.216553462,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "aea984fa-980f-11ed-b64d-06206ad47459",
        "name": "Toilet (32302)",
        "description": null,
        "latitude": 55.359626681,
        "longitude": 9.21657551,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "aec5a63a-980f-11ed-b7c5-06206ad47459",
        "name": "Toilet (32591) - Skodborg Børnesø",
        "description": "I udkanten af Skodborg Børneskov og med udsigt over vand og med aftensol ligger en ny bålhytte/shelter og et seperat liggende shelter. Her kan du falde i søvn til de sidste gløders lys og vågne op med et kærligt \"rap\" fra andemor i søen.",
        "latitude": 55.420509392,
        "longitude": 9.157348395,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "1642b934-9af3-11ed-bda8-06206ad47459",
        "name": "Toilet (32722)",
        "description": null,
        "latitude": 55.547356334,
        "longitude": 9.090456942,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "16494a7e-9af3-11ed-bdb2-06206ad47459",
        "name": "Toilet (33126)",
        "description": null,
        "latitude": 55.507545143,
        "longitude": 8.938807,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "1649b590-9af3-11ed-bdb8-06206ad47459",
        "name": "Toilet (33688)",
        "description": null,
        "latitude": 55.455441459,
        "longitude": 9.144425388,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "aeaa85e4-980f-11ed-b657-06206ad47459",
        "name": "Toilet (35086)",
        "description": null,
        "latitude": 55.477252455,
        "longitude": 9.1465134,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "164b5f44-9af3-11ed-bdba-06206ad47459",
        "name": "Toilet (35258) - Rasteplads Gamst Søenge",
        "description": "Det er en rasteplads med god udsigt over landskabet og mange borde/bænkesæt, som gør det både nemt og hyggeligt at sætte sig med den medbragte kaffe. \nRastepladsen er let tilbagetrukket fra vejen, og de mange høje træer giver en fornemmelse af ro",
        "latitude": 55.470692612,
        "longitude": 9.192315066,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "1654fc16-9af3-11ed-bdc4-06206ad47459",
        "name": "Toilet (36182)",
        "description": null,
        "latitude": 55.60583926,
        "longitude": 8.937087354,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "16567c58-9af3-11ed-bdc6-06206ad47459",
        "name": "Toilet (36183)",
        "description": null,
        "latitude": 55.606180401,
        "longitude": 8.936430677,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "eb1e2115-807e-4d32-b543-a56694d06e4a",
        "name": "Toilet (43902) - Shelterplads ved Bække",
        "description": "Tæt på den store Hamborggårdsten ligger KFUM-spejdernes Klebækhytte. I tilknytning til hyttens udeareal er den en teltplads, som kan anvendes af vandrere og cyklister. På arealet der der desuden etableret to shelters med  plads til 4-5 personer i hv",
        "latitude": 55.581760417,
        "longitude": 9.149403265,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "852025bc-8b67-11ed-b45e-06206ad47459",
        "name": "Toilet (43905) - Hovborg Naturudstilling",
        "description": "Naturudstillingen fortæller om Hovborg-områdets udvikling fra gletchere til agerland, fra hosekræmmere til Dalgas og frem til nutidens Hovborg. Den lille udstilling giver dermed forklaring på meget af det, du kan se på gå-turene langs med Holme Å.",
        "latitude": 55.608646217,
        "longitude": 8.935000054,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "4c5e8e2b-0a92-41d6-ab4a-c5cd002ab478",
        "name": "Toilet (44345) - Shelterplads ved Hovborg",
        "description": "Der er plads til 2-3 telte på den indhegnede plads, og man er velkommen til at benytte det faste bålsted, og der findes brænde på pladsen. Der er i vinteren 2017 blevet udvidet med et nyt shelter med plads til 4-5 personer.",
        "latitude": 55.608642923,
        "longitude": 8.92662652,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "8524b3de-8b67-11ed-b464-06206ad47459",
        "name": "Toilet (44397) - Teltplads ved Hermansens Høj",
        "description": "Ved Hermansens Høj 800 m efter Knag Mølle Bro er det muligt at lægge til ved bredden af åen og benytte bålplads og bord/bænkesæt på den lille bakke kaldet Hermansens Høj.",
        "latitude": 55.442475955,
        "longitude": 9.15433684,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "6ebf0dcc-b17a-4008-ae14-63847591d249",
        "name": "Toilet (44465) - Shelterplads ved Frihedsbroen",
        "description": "Shelterpladsen ved Frihedsbroen ligger smukt ned til åen.",
        "latitude": 55.434722406,
        "longitude": 9.109091118,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "adb9a5f9-32d3-41ec-8cd7-bf046edbb8ce",
        "name": "Toilet (44466) - Shelterplads ved Skudstrup",
        "description": "Denne shelterplads ligger ved lerdueskydebanerne i Skudstrup. Der er en smuk udsigt over Kongeådalen fra de to shelters.",
        "latitude": 55.446270171,
        "longitude": 9.190194588,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "8525c422-8b67-11ed-b466-06206ad47459",
        "name": "Toilet (44467) - Rasteplads ved Villebøl Bro",
        "description": "På nordsiden af Kongeåen ved Villebøl Bro findes en rasteplads, hvor man kan parkere. Der går to rundture ud fra P-pladsen, men er også muligt at gå længere mod både øst og vest.",
        "latitude": 55.408430156,
        "longitude": 8.885368425,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "70585b91-906e-4fe7-af31-c5f2b448b61d",
        "name": "Toilet (44468) - Shelterplads ved Vejen Fodboldgolf",
        "description": "På denne plads kan man benytte de faciliteter der findes på Vejen Fodboldgolf. Her er et stort overdækket areal med bord/bænkesæt,  hvor du kan nyde madpakken, samt geo, bruser, handicapgeo og vand.",
        "latitude": 55.441442592,
        "longitude": 9.148898656,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "c21f7831-96fc-41c3-91a7-2a4a1f1f4bb1",
        "name": "Toilet (44470) - Rasteplads ved Skodborghus",
        "description": "Ved Skodborghus ligger syd for Kongeåen en rasteplads, hvor man kan parkere. Her findes en digital infoskærm med gratis wi-fi og mulighed for at downloade APP og kort til turen.",
        "latitude": 55.441883001,
        "longitude": 9.136637972,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "3a1c80ce-1151-11ec-9d9a-0661a94bd4ab",
        "name": "Toilet (44900) - Shelterplads ved Skelhøj",
        "description": "Denne shelterplads ligger lige ved siden af Kongeåen. Der er to shelters i en lille lund og bålplads tæt på åen med bord og bænke.",
        "latitude": 55.411347264,
        "longitude": 8.876902643,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "163fc850-9af3-11ed-bda6-06206ad47459",
        "name": "Toilet (32721)",
        "description": null,
        "latitude": 55.546322304,
        "longitude": 9.089235693,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "b34021f6-9b23-11ed-83d3-06206ad47459",
        "name": "Toilet (46109)",
        "description": null,
        "latitude": 55.523077279,
        "longitude": 9.096433982,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "56f48bc6-1163-11ec-b4a9-0661a94bd4ab",
        "name": "Jernved Kulturhus geo",
        "description": "Toilet med vandhane, Jernvedlund kulturhus. Åbent under kulturhusets åbningstider. Lukket på hellighdage.",
        "latitude": 55.42138693,
        "longitude": 8.795738853,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "2c677f09-38ab-483d-b6e2-dfd20686fb3a",
        "name": "Dørup hestestald",
        "description": null,
        "latitude": 56.025183204,
        "longitude": 9.758617065,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/dbea3136-1db7-490d-86ab-8a6e50471827.jpg"
        ]
      },
      {
        "id": "674aa927-af68-44d9-ad07-4d1fb9993ec2",
        "name": "Sønder Ege",
        "description": null,
        "latitude": 56.098766979,
        "longitude": 9.762456429,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/13f7ef98-bbf0-47de-ae83-35e8e2dc9fae.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/0d51d72e-e6ef-4deb-983e-7e51e962473b.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/a482b113-dc7c-4a31-86f6-239afc6a09b8.jpg"
        ]
      },
      {
        "id": "2fbfb1a1-0f49-4a37-b50c-f8f21f9f04e3",
        "name": "Søbadet",
        "description": null,
        "latitude": 56.022009107,
        "longitude": 9.926181356,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/8a3431e2-23df-439a-9bdf-f17f0ae9894e.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/651d24ac-d687-42f7-b1bc-edd5caf47967.jpg"
        ]
      },
      {
        "id": "00c0fc03-3ecc-4a1a-a2c4-49c83a1c2c27",
        "name": "Faunapassagen",
        "description": null,
        "latitude": 56.090377795,
        "longitude": 9.752925282,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/cd7db2f6-41ee-4f1a-a3fb-46a7706aa07b.jpg"
        ]
      },
      {
        "id": "8f219176-40c0-4c1a-b501-9c79f3840d23",
        "name": "Knøsen",
        "description": null,
        "latitude": 56.110693347,
        "longitude": 9.649211422,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/eb203ace-8904-4ef8-9514-57608165f777.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/736bd86f-da7d-4e2c-a4b1-63f041d5b465.jpg"
        ]
      },
      {
        "id": "839d68c6-0d1d-4331-b01b-9aa42d85085b",
        "name": "Junges Plantage",
        "description": null,
        "latitude": 56.024355375,
        "longitude": 9.903040972,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/306a2deb-f06e-40c1-a294-266ae2acbf82.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/ce2515cd-f15b-4ad0-9dd4-68ff1734115f.jpg"
        ]
      },
      {
        "id": "0b2cfae4-692a-11ef-970c-cbcd6e79bf07",
        "name": "Brandhøjbanen",
        "description": "Toilet ved Brandhøj Banens bygninger",
        "latitude": 55.63794047,
        "longitude": 12.185715437,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "ed53a41a-669e-11ef-b61d-3f20b877938a",
        "name": "Varmecentralen, Sankt Hans",
        "description": "Toilet  i varmecentralen i Sankt Hans",
        "latitude": 55.655074,
        "longitude": 12.057645321,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "c203f7c2-ae75-11eb-b1a7-06d09fa510f4",
        "name": "Toilet ved Flintebjerg, Hedeland",
        "description": "Toilet ved Flintebjerg, Hedeland",
        "latitude": 55.628367791,
        "longitude": 12.174258117,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "9dfd4afe-ae75-11eb-b17d-06d09fa510f4",
        "name": "P-plads ved Flintebjerg, Hedeland",
        "description": "P-plads ved Flintebjerg, Hedeland",
        "latitude": 55.624448208,
        "longitude": 12.173123673,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "a6b4defe-6908-11ef-9189-ed57e5b6d801",
        "name": "Lützhøfts Købmandsgaard",
        "description": "Toilet i gården til Museet: Lützhøfts Købmandsgaard",
        "latitude": 55.640053809,
        "longitude": 12.075562477,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "163f87aa-9af3-11ed-bda4-06206ad47459",
        "name": "Toilet (32077)",
        "description": null,
        "latitude": 55.376884872,
        "longitude": 9.08069069,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "39b991f2-93d3-49b5-a4c7-815c124b31fe",
        "name": "Bytoften",
        "description": null,
        "latitude": 56.153732283,
        "longitude": 9.900202954,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/ac1054a9-f73d-43d3-82ed-c8e60ee02229.jpg"
        ]
      },
      {
        "id": "7c94931a-a4dd-43a9-b957-9a50ca682a52",
        "name": "Vestermølle",
        "description": null,
        "latitude": 56.028621323,
        "longitude": 9.957907147,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/24bac249-0c9f-4fd7-95d8-13a47296c098.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/504cb442-9f44-4f09-b219-f6d32fcbfea6.jpg"
        ]
      },
      {
        "id": "10da58ba-3996-4ca5-b366-520d1666aa2d",
        "name": "Ry Station",
        "description": null,
        "latitude": 56.091498918,
        "longitude": 9.758331928,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/9aeb7e00-251b-47e9-ab75-d8f9799fafb8.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/c34e26f0-d2cb-4319-beae-b2ec9c73769e.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/fbbf05e6-df8c-41ef-b072-3e1a5401d561.jpg"
        ]
      },
      {
        "id": "c2abf9ea-4e23-4811-a04e-ed40c36361b6",
        "name": "Illerup Ådal",
        "description": null,
        "latitude": 56.058387126,
        "longitude": 9.912618646,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/10f0e9fe-251e-4ef1-93ec-c154c9721523.jpg"
        ]
      },
      {
        "id": "dcb7df52-a13e-469e-aa5b-c26ba6dba40b",
        "name": "Skimminghøj",
        "description": null,
        "latitude": 56.095566884,
        "longitude": 9.74781607,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/8a295e7f-1e4e-4173-a737-fa568bb11ecc.jpg"
        ]
      },
      {
        "id": "2f0b825e-7f30-491d-b4f5-be2040a88af2",
        "name": "Toilet ved Endelave Kirke",
        "description": "Handicapvenligt geo ved Endelave kirke",
        "latitude": 55.758659597,
        "longitude": 10.268611908,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/ddac7d9a-a932-491d-9009-084c0716db7b.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/81f507b4-78fb-4459-8cce-b300d56d83dd.jpg"
        ]
      },
      {
        "id": "570fef9c-1163-11ec-b639-0661a94bd4ab",
        "name": "Toilet Ved Kongeåslusen",
        "description": "Håndspritsautomat og geopapir tilgængeligt. Rengøres 2 gange ugentligt.",
        "latitude": 55.37988666,
        "longitude": 8.648297045,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "56eb12e4-1163-11ec-b41f-0661a94bd4ab",
        "name": "Toilet",
        "description": "Træk-og-skyl geo med vandhane",
        "latitude": 55.402202305,
        "longitude": 8.735784777,
        "paymentRequired": false,
        "photoUrls": []
      },
      {
        "id": "01372262-2d7c-4e88-a837-6ef89242567a",
        "name": "muldgeo shelterplads Søndre Sø - Blokhus Klitplantage",
        "description": "Muldgeo ved den primitive overnatningsplads",
        "latitude": 57.232682575,
        "longitude": 9.573772376,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "10f1690b-3ba1-4e9b-a6b0-eb97959c57d3",
        "name": "Østerskov Midtvej",
        "description": "Muldgeo",
        "latitude": 56.13533647,
        "longitude": 9.589397041,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/f73c485a-119e-4041-9805-0ceda6df6958.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/07c7f6ae-7ae0-4992-97fd-fade93f29080.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/a7935bbd-ebcb-4998-a346-11902f110162.jpg"
        ]
      },
      {
        "id": "13004fd9-24c8-42cc-af26-18712c8a3daf",
        "name": "Muldgeo ved Julsø teltplads",
        "description": null,
        "latitude": 56.109139198,
        "longitude": 9.691815874,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/e4df369a-d393-45c7-b650-0ca8f02a0820.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/a4e7b12d-c10a-49f7-9bf1-42ab9b0cda1c.jpg"
        ]
      },
      {
        "id": "1b5ab104-5d0d-433a-ba60-68ca7872b029",
        "name": "Vigsø lejrplads",
        "description": "Muldgeo",
        "latitude": 57.090850332,
        "longitude": 8.741645124,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "1c6869c4-2d00-418f-9cb6-37ea4513b96d",
        "name": "Ikast Byskov",
        "description": "Muldgeo i krat",
        "latitude": 56.102199877,
        "longitude": 9.216198999,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/2580c225-2b27-47ac-ab29-a4183799dc42.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/8eabfa32-61d3-4d8b-af78-2ff6a296dd53.jpg"
        ]
      },
      {
        "id": "3dde5494-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.7210571,
        "longitude": 12.4143452,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "3de1d038-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.7117526,
        "longitude": 12.3785276,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "3de25526-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.7281365,
        "longitude": 12.358346,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "3de2f904-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.7296394,
        "longitude": 12.35967,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "3de36510-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.7300797,
        "longitude": 12.3578576,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "3de3b59c-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.733322973,
        "longitude": 12.39941004,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "3de45ce0-15da-11f0-88df-a135c5824abf",
        "name": null,
        "description": null,
        "latitude": 55.731628876,
        "longitude": 12.36166708,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "42eaab70-6f90-48de-9e17-205fc8323db8",
        "name": "Toilet ved Sundets Bådelaug / Sølystvej 9",
        "description": "Toilet ved Sundets Badelaugs p-plads og ved Husoddestien",
        "latitude": 55.866337746,
        "longitude": 9.893321152,
        "paymentRequired": false,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/11e4fe1e-bf5e-4488-b2cf-f699436074e2.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/093dfcf4-b0ad-43f0-83b5-115e8ac0d0ce.jpg",
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/f5098c53-15ee-4f9a-b1d3-29f8a8436d07.jpg"
        ]
      },
      {
        "id": "61cddbdc-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Ligger til venstre for P-pladsen",
        "description": null,
        "latitude": 56.097466514,
        "longitude": 12.160502984,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61ce5c38-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Ved P-plads",
        "description": null,
        "latitude": 56.119637218,
        "longitude": 12.236689105,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61cf345a-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Ligger til højre for ”DanCentre”",
        "description": null,
        "latitude": 56.123708307,
        "longitude": 12.260853787,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61cfc8d4-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Ligger i strandbakkerne",
        "description": null,
        "latitude": 56.122356333,
        "longitude": 12.32038514,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61d062d0-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Gilleleje Havn",
        "description": null,
        "latitude": 56.125135339,
        "longitude": 12.311743346,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "54e11056-7ace-4ab3-82de-78c9d2bc5294",
        "name": "Toiletter i Fjeldsø",
        "description": "Fjelsø Klostrup beboerforening har oprettet nye handicapgeoter med tilhørende madpakkehus, shelter og bålplads. Det er beliggende på Ettrupvej 2, Fjelsø, 9620 Aalestrup, lige ved sø bredden.",
        "latitude": 56.664510558,
        "longitude": 9.434739278,
        "paymentRequired": null,
        "photoUrls": [
          "https://mapcentia-www.s3-eu-west-1.amazonaws.com/fkg/439c1735-82f2-4546-8855-5ba56a1f189f.jpg"
        ]
      },
      {
        "id": "61d13386-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Bagerst på parkeringspladsen",
        "description": null,
        "latitude": 56.055118378,
        "longitude": 12.057609953,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61d1f10e-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "På parkeringsplads",
        "description": null,
        "latitude": 56.119895614,
        "longitude": 12.236719521,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61d28088-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "Heatherhill's parkeringsplads",
        "description": null,
        "latitude": 56.086359218,
        "longitude": 12.143086057,
        "paymentRequired": null,
        "photoUrls": []
      },
      {
        "id": "61d3130e-2bf5-11f0-8fd3-b3eb31aa6320",
        "name": "På bagsiden af restaurant",
        "description": null,
        "latitude": 56.022942724,
        "longitude": 12.1985966,
        "paymentRequired": null,
        "photoUrls": []
      }
    ]
""".trimIndent()
