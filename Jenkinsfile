pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'keshavkumarvinbox/java-k8s-app'
        DOCKER_TAG   = 'latest'
        KUBE_DEPLOYMENT = 'java-app'
        KUBE_CONTAINER  = 'java-app'
        KUBE_NAMESPACE  = 'default'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/keshavkumar-web/java-k8s-cicd.git'
            }
        }

        stage('Build Java App') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry(
                    credentialsId: 'dockerhub-credentials',
                    url: 'https://index.docker.io/v1/'
                ) {
                    sh 'docker push ${DOCKER_IMAGE}:${DOCKER_TAG}'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                  kubectl apply -f deployment.yaml
                  kubectl apply -f service.yaml
                  kubectl rollout status deployment/java-app
                '''
            }
        }
    }
}

