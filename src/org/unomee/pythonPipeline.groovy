#!/usr/bin/groovy
package org.unomee;

pythonPipeline(pipelineDefinition) {
    // Create a globally accessible variable that makes
    // the YAML pipeline definition available to all scripts
    pd = pipelineDefinition
}

def someFunc(String skuskatextu) {
    echo skuskatextu
    skuskatextu
}


def executePipeline() {
    node {

/***
        if (runTests) {
            stage('Run Tests') {
                sh pd.testCommand
            }
        }

        if (deployUponTestSuccess) {
            stage('Deploy') {
                sh "path/to/a/deploy/bash/script.sh ${pd.deploymentEnvironment}"
            }
        }
 **/
        stage 'staging'
        def first = someFunc('this is first stage!!!!!')
        def second = someFunc('this is second stage !!!!')
    }
}

return this