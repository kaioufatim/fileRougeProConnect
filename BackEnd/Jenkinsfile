pipeline{
  agent any
tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

  stages{
        stage('Build'){
            steps{
                bat 'mvn -Dmaven.test.skip=true clean install'
            }
        }
        stage('Test Service'){
              steps{
                  bat 'mvn test -Dtest=CreateurServiceImpTest'
                  bat 'mvn test -Dtest=EntrepreneurServiceImpTest'

              }
        }
        stage('Test Controller'){
                      steps{
                         bat 'mvn test -Dtest=CreateurControllerTesting'
                         bat 'mvn test -Dtest=EntrepreneurControllerTesting'

                      }
        }
         stage('Construction de l\'image Docker') {
                    steps {
                        script {
                            bat 'docker build -t projectimage:1.0.0 .'
                        }
                    }
         }
          stage('Deploy Docker Image') {
                     steps {
                         script {
                             withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerHub')]) {
                             bat 'docker login -u kaioufatimazahra@gmail.com -p ${dockerhubpwd}'
                             }

                            bat 'docker push kaioufatimazahra@gmail.com/projectimage:1.0.0'
                         }
                     }
                 }


  }
}