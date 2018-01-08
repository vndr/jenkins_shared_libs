#!/usr/bin/groovy
package org.unomee;

pythonPipeline(pipelineDefinition['pipelineType']) {
    // Create a globally accessible variable that makes
    // the YAML pipeline definition available to all scripts
    pd = pipelineDefinition
    println("This is pipelineDefinition inside pythonPipeline: " + pd)
}

def executePipeline() {
    node {

        if (pipelineDefinition['runTests']) {
            stage('Run Tests') {
//                sh pd.testCommand
                printl("test is running")
            }
        }

        if (pipelineDefinition['deployUponTestSuccess']) {
            stage('Deploy') {
//                sh "path/to/a/deploy/bash/script.sh ${pd.deploymentEnvironment}"
                printl("App is deployed")

            }
        }
    }
}

return this