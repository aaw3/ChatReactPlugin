# Build
In your java editor of choice with maven installed, find chatreactplugin (com.aaw3.chatreactplugin:chatreactplugin) run compile then install

# Install
Place in your plugins directory and load the server. A conifugration folder will open with a sounds.yml to add custom messages and parameters assocated with them

# Texture Pack Setup
Create a default texture pack. This consists of:
- 64x64 pack.png
- pack.mcmeta containing: { "pack": { "description": "Pack Name", "pack_format": 15 }}
- assets folder

In the assets folder create a folder named minecraft and inside that make a folder named sounds.json

In the minecraft folder also create a sounds.json file and insert the following content.

```
{ "custom.sound1": {
    "category": "master",
    "sounds": ["custom/sound1"] },
  "custom.sound2": {
    "category": "master",
    "sounds": ["custom/sound2"] },
}
```

### sound1 and sound2 should exist inside the sounds.yml like this:

```
- soundID: minecraft:custom.sound1
  phrases: [hello, hi, what's up]
  matchIfContains: true
  caseSensitive: false
  ignorePunctuation: true
  <br>
- soundID: minecraft:custom.sound2
  phrases: [goodbye]
  matchIfContains: true
  caseSensitive: false
  ignorePunctuation: true
```

sound1/sound2 can be name whatever, however it must match in sounds.yml and the texturepack.

Once you have added all of your sounds to the texture pack, zip the folder and name it ``server_pack.zip``

## server.properies setup

Store the file on a CDN and make it accessible to the public. I used google drive in my case so once uploaded and made private, copy the share url and format the characters, ``:``, ``=``, like ``\:``, ``\=`` so that it can be parsed properly in the server.properies
<br>
`server.properies example`:
```
resource-pack=https\://drive.google.com/uc?export\=download&id\=9uqa-afewsjop9u5_AWj9JQkfowe-3_q
```

Once you restart your server you should be prompted to install a texture pack which is required to hear the custom sounds properly