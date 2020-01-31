import argparse, sys
import os
import __future__

#print(os.environ['HOME'])

parser=argparse.ArgumentParser()

# The stage which should be executed
parser.add_argument('stage', help='The current stage of the make file')

#parser.add_argument('--stage', help='The current stage of the make file')

# general information which are used during the buildind phase
parser.add_argument('--project_name', help='The name of the project')
parser.add_argument('--org_name', help='The name of the organisation')
parser.add_argument('--repo_name', help='The name of the github repo')
parser.add_argument('--file', help='The location of the compose file which will be used')
parser.add_argument('--build_id', help='The build id of the project')

args=parser.parse_args()

print(args)

stage = args.stage

if stage == "init":
    print("start all dependencies")
    # todo parse compose file and run services
    result = os.system("docker-compose -f " + args.file + " up redis")

if stage == "test":
    print("Starte mit dem Testen der Anwendung...")
    errorCode = os.system("docker-compose -f " + args.file + " run target")
    if errorCode != 0:
        print("Tests failed...")
        raise Exception(errorCode)

    print("Tests wurden erfolgreich durchlaufen...")

    #result = os.system("docker-compose -f " + args.file + " rm -f")

if stage == "build":
    print("Hello from building")

if stage == "release":
    print("Hello from release")

if stage == "clean":
    print("Hello from clean")
    result = os.system("docker-compose -f " + args.file + " kill")
    result = os.system("docker-compose -f " + args.file + " rm -f")
    



if stage == "cmdtest":
    print("Hello from cmdtest")
    cmd = "type make.py"
    result = os.system(cmd)
    print(result)
    cmd = "type make.dpy"
    result = os.system(cmd)
    print(result)



#print sys
