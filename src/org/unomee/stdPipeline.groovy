#!/bin/groovy
package org.unomee;
@Grab('org.yaml:snakeyaml:1.17')

import org.yaml.snakeyaml.Yaml

def execute() {

    node {

        stage('Initialize') {
            checkout scm
            echo 'Loading pipeline definition'
            Yaml parser = new Yaml()
            Map pipelineDefinition = parser.load(new File(pwd() + '/pipeline.yml').text)
//            List pipelineDefinition = parser.load((new File(pwd) + '/pipeline.yaml').text)

        }

        switch(pipelineDefinition.pipelineType) {
            case 'python':
                // Instantiate and execute a Python pipeline
                new pythonPipeline(pipelineDefinition).executePipeline()
//            case 'nodejs':
                // Instantiate and execute a NodeJS pipeline
                // new nodeJSPipeline(pipelineDefinition).executePipeline()
        }

    }

}