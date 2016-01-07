node ('kubernetes'){
  git GIT_URL
  withEnv(["PATH+MAVEN=${tool 'cub-3.3.1'}/bin"]) {

    sh 'mvn clean install'
  }
}
