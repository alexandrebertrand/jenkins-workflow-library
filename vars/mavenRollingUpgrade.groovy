def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def flow = new io.fabric8.Fabric8Commands()
    def fabric8MavenPluginVersion = flow.getReleaseVersion "io/fabric8/fabric8-maven-plugin"

    stage "Rolling upgrade ${config.environment}"

    sh "mvn io.fabric8:fabric8-maven-plugin:${fabric8MavenPluginVersion}:json io.fabric8:fabric8-maven-plugin:${fabric8MavenPluginVersion}:rolling -Dfabric8.environment=${config.environment} -Dfabric8.dockerUser=fabric8/ -Ddocker.registry=${env.FABRIC8_DOCKER_REGISTRY_SERVICE_HOST}:${env.FABRIC8_DOCKER_REGISTRY_SERVICE_PORT}"

  }
