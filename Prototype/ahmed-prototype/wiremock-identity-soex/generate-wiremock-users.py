import csv
import json
import os

os.makedirs('mappings', exist_ok=True)

with open('users.csv', newline='', encoding='utf-8') as csvfile:
    reader = csv.DictReader(csvfile)
    for row in reader:
        stub = {
            "request": {
                "method": "POST",
                "url": "/login",
                "bodyPatterns": [
                    {
                        "matchesJsonPath": f"$[?(@.username == '{row['gebruikersnaam']}' && @.password == '{row['wachtwoord']}')]"
                    }
                ]
            },
            "response": {
                "status": 200,
                "headers": {
                    "token": "{{randomValue length=16 type='ALPHANUMERIC'}}"
                },
                "transformers": ["response-template"]
            }
        }
        # Define the filename based on the username
        filename = f"mappings/login-{row['gebruikersnaam']}.json"
        # Write the stub to a JSON file
        with open(filename, 'w', encoding='utf-8') as jsonfile:
            json.dump(stub, jsonfile, ensure_ascii=False, indent=4)
        print(f"Created stub for user: {row['gebruikersnaam']}")
