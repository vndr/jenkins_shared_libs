#!/bin/groovy
package org.unomee;
import org.unomee.*;

@Grab('org.yaml:snakeyaml:1.17')

import org.yaml.snakeyaml.Yaml

def execute() {

    def pipelineDefinition = [:]
    node {

        stage('Initialize') {
            checkout scm
            echo 'Loading pipeline definition'
            Yaml parser = new Yaml()
            pipelineDefinition = parser.load(new File(pwd() + '/pipeline.yaml').text)
//            List pipelineDefinition = parser.load((new File(pwd) + '/pipeline.yaml').text)
            println("This is array for pipelineDefiniton.pipelineType: " + pipelineDefinition['pipelineType'])
        }

        switch(pipelineDefinition['pipelineType']) {
            case 'python':
                // Instantiate and execute a Python pipeline
                new pythonPipeline().executePipeline(pipelineDefinition)
            case 'nodejs':
                // Instantiate and execute a NodeJS pipeline
                new nodeJSPipeline().executePipeline(pipelineDefinition)
            case 'java':
                // Instantiate and execute a NodeJS pipeline
                new javaPipeline().executePipeline(pipelineDefinition)
        }

    }

}